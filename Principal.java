

import java.util.Scanner;

public class Principal {
    static Scanner leer = new Scanner(System.in);
    static final int elementos=5; 
    static Productos[] productos = new Productos[elementos];
    static Productos producto = new Productos();
    static Clientes[] clientes = new Clientes[elementos];
    static Clientes cliente = new Clientes();
    static Ventas[] ventas = new Ventas[elementos];
    static Ventas venta;
    static int op, opCrud, np=3, nc=3, nv=0, pos, opPro, opCli, opVen, id=0, ndp;
    static String codigo, documento, recibo, fecha = "27/05/2023";
    
    public static void main(String[] args) {
        producto.codigo = "0908";
        producto.nombre = "Pepsi";
        producto.precio = 3000;
        productos[0] = producto;
        
        producto = new Productos();
        producto.codigo = "7826";
        producto.nombre = "CocaCola";
        producto.precio = 4500;
        productos[1] = producto;
        
        producto = new Productos();
        producto.codigo = "1234";
        producto.nombre = "Poni";
        producto.precio = 2500;
        productos[2] = producto;
        
        cliente.nroDocumento = "2354";
        cliente.apellido = "Lopez";
        cliente.nombre = "Laura";
        cliente.celular = "3204000000";
        clientes[0] = cliente;
        
        cliente = new Clientes();
        cliente.nroDocumento = "6578";
        cliente.apellido = "Castro";
        cliente.nombre = "Camilo";
        cliente.celular = "3204000001";
        clientes[1] = cliente;
        
        cliente = new Clientes();
        cliente.nroDocumento = "2354";
        cliente.apellido = "Jimenez";
        cliente.nombre = "Juan";
        cliente.celular = "3204000002";
        clientes[2] = cliente;
        
        
        do{
            limpiarPantalla();
            menuPrincipal();
            System.out.println("Digite la opcion");
            op = leer.nextInt();
            switch(op){
                case 1://Productos
                menuProductos();
                break;
                case 2://Clientes
                menuClientes();
                break;
                case 3://Ventas
                menuVentas();
                break;
            }
        }while(op != 4);
    }
    
    public static void menuVentas(){
        do{
            limpiarPantalla();
            System.out.println("--- Ventas ---");
            menuCud2();
            System.out.println("\nDigite la opción");
            opCrud = leer.nextInt();
            switch(opCrud){
                case 1://Registrar Ventas
                    if (nv < elementos) {
                        venta = new Ventas();
                        System.out.println("Digite documento del cliente");
                        documento = leer.next();
                        pos = consultarCliente(documento);
                        if (pos > -1) {
                            mostrarCliente(pos);
                            id = id + 1;
                            venta.num = id;
                            venta.cliente = documento;
                            venta.fecha = fecha;
                            do{
                                listarProductos();
                                System.out.println("\nDigite codigo del producto");
                                codigo = leer.next();
                                pos = consultarProducto(codigo);
                                if(pos > -1){
                                    venta.productos[ndp] = codigo;
                                    ndp = ndp + 1;
                                    
                                }
                                System.out.println("\n¿Desea comprar otro producto?: Si(1) - No(2)");
                                opVen = leer.nextInt();
                            }while(opVen == 1);
                            System.out.println("\n La venta fue registrada con exito");
                            ventas[nv] = venta;
                            nv = nv + 1;
                            pausa();
                        }else{
                            System.out.println("El cliente no esta registrado");
                            pausa();
                        }
                    }else{
                        System.out.println("El vector esta lleno, no es posible registrar otra venta");
                    }
                break;
                case 2://Listar Ventas
                listarVentas();
                pausa();
                break;
                case 3://Modificar Ventas
                System.out.println("Digite el numero del recibo");
                    recibo = leer.next();
                    pos = consultarVenta(recibo);
                    if(pos == -1){
                        System.out.println("\nLa venta no esta registrada");
                        pausa();
                    }else{
                        mostrarVenta(pos);
                        System.out.println("\n¿Que desea modificar?");
                        System.out.println("1. Fecha");
                        System.out.println("2. Nro. recibo");
                        System.out.println("3. Documento del cliente");
                        System.out.println("Digite la opcion: ");
                        opCrud = leer.nextInt();
                        switch(opCrud){
                            case 1:
                            System.out.println("Digite la nueva fecha");
                            ventas[pos].fecha = leer.next();
                            break;
                            case 2:
                            System.out.println("Digite el nuevo numero de recibo");
                            ventas[pos].num = leer.nextInt();
                            break;
                            case 3:
                            System.out.println("Digite el nuevo documento");
                            ventas[pos].cliente = leer.next();
                            break;
                        }
                        System.out.println("\nDatos actualizados con exito");
                        mostrarVenta(pos);
                        pausa();
                    }
                break;
            }
        }while(opCrud != 4);
    }
    public static void menuClientes(){
        do{
            limpiarPantalla();
            System.out.println("--- Clientes ---");
            menuCrud();
            System.out.println("\nDigite la opción");
            opCrud = leer.nextInt();
            switch(opCrud){
                case 1://Registrar Clientes
                if(nc < elementos){
                    System.out.println("Digite el documento");
                    documento = leer.next();
                    pos = consultarCliente(documento);
                    if(pos == -1){
                       cliente = new Clientes();
                       cliente.nroDocumento = documento;
                       System.out.println("Digite el nombre");
                       cliente.nombre = leer.next();
                       System.out.println("Digite el apellido");
                       cliente.apellido = leer.next();
                       System.out.println("Digite numero de celular");
                       cliente.celular = leer.next();
                       clientes[nc] = cliente;
                       System.out.println("\nCliente registrado con exito");
                       pausa();
                       nc++;
                    }else{
                        System.out.println("\n El cliente se encuentra registrado");
                        mostrarCliente(pos);
                        pausa();
                    }
                }else{
                    System.out.println("El vector esta lleno, no es posible registrar otro cliente");
                    pausa();
                }
                break;
                case 2://Listar Clientes
                listarClientes();
                pausa();
                break;
                case 3://Modificar Clientes
                    System.out.println("Digite el documento");
                    documento = leer.next();
                    pos = consultarCliente(documento);
                    if(pos == -1){
                        System.out.println("\nEl cliente no esta registrado");
                        pausa();
                    }else{
                        mostrarCliente(pos);
                        System.out.println("\n¿Que desea modificar?");
                        System.out.println("1. Documento");
                        System.out.println("2. Apellido");
                        System.out.println("3. Nombre");
                        System.out.println("4. Celular");
                        System.out.println("Digite la opcion: ");
                        opCrud = leer.nextInt();
                        switch(opCrud){
                            case 1:
                            System.out.println("Digite el nuevo documento");
                            clientes[pos].nroDocumento = leer.next();
                            break;
                            case 2:
                            System.out.println("Digite el nuevo apellido");
                            clientes[pos].apellido = leer.next();
                            break;
                            case 3:
                            System.out.println("Digite el nuevo nombre");
                            clientes[pos].nombre = leer.next();
                            break;
                            case 4:
                            System.out.println("Digite el nuevo celular");
                            clientes[pos].celular = leer.next();
                            break;
                        }
                        System.out.println("\nDatos actualizados con exito");
                        mostrarCliente(pos);
                        pausa();
                    }
                break;
                case 4://Eliminar Clientes
                System.out.println("Digite el documento");
                documento = leer.next();
                pos = consultarCliente(documento);
                if(pos == -1){
                    System.out.println("\n El cliente no esta registrado");
                    pausa();
                }else{
                    mostrarCliente(pos);
                    System.out.println("\n¿Esta seguro que desea eliminar este cliente? Si(1)  No(2)");
                    opCli = leer.nextInt();
                    if(opCli == 1){
                        for(int i=pos; i<=nc-1; i++){
                            clientes[i] = clientes[i+1];
                        }
                        nc = nc - 1;
                        System.out.println("\nCliente eliminado");
                        pausa();
                    }
                }
                break;
            }
        }while(opCrud != 5);
    }
    public static void menuProductos(){
        do{
            limpiarPantalla();
            System.out.println("--- Productos ---");
            menuCrud();
            System.out.println("\nDigite la opción");
            opCrud = leer.nextInt();
            switch(opCrud){
                case 1://Registrar Productos
                    if(np < elementos){
                        System.out.println("Digite el codigo del producto");
                        codigo = leer.next();
                        pos = consultarProducto(codigo);
                        if(pos == -1){
                            producto = new Productos();
                            producto.codigo = codigo;
                            System.out.println("Digite el nombre del producto");
                            producto.nombre = leer.next();
                            System.out.println("Digite el precio del producto");
                            producto.precio = leer.nextDouble();
                            productos[np] = producto;
                            System.out.println("\nEl producto fue registrado con exito");
                            pausa();
                            np++;
                        }else{
                            System.out.println("\n El producto se encuentra registrado");
                            mostrarProducto(pos);
                            pausa();
                        }
                    }else{
                        System.out.println("El vector esta lleno, no es posible registrar otro producto");
                        pausa();
                    }
                break;
                case 2://Listar Productos
                    listarProductos();
                    pausa();
                break;
                case 3://Modificar Productos
                    System.out.println("Digite el codigo del producto");
                    codigo = leer.next();
                    pos = consultarProducto(codigo);
                    if(pos == -1){
                        System.out.println("\n El producto no esta registrado");
                        pausa();
                    }else{
                        mostrarProducto(pos);
                        System.out.println("\n¿Que desea modificar?");
                        System.out.println("1. Codigo");
                        System.out.println("2. Nombre");
                        System.out.println("3. Precio");
                        System.out.println("Digite la opción: ");
                        opCrud = leer.nextInt();
                        switch(opCrud){
                            case 1:
                            System.out.println("Digite el nuevo codigo");
                            productos[pos].codigo = leer.next();
                            break;
                            case 2:
                            System.out.println("Digite el nuevo nombre");
                            productos[pos].nombre = leer.next();
                            break;
                            case 3:
                            System.out.println("Digite el nuevo precio");
                            productos[pos].precio = leer.nextDouble();
                            break;
                        }
                        System.out.println("\nDatos actualizados con exito");
                        mostrarProducto(pos);
                        pausa();
                    }
                break;
                case 4://Eliminar Productos
                System.out.println("Digite el codigo del producto");
                codigo = leer.next();
                pos = consultarProducto(codigo);
                if(pos == -1){
                    System.out.println("\n El producto no esta registrado");
                    pausa();
                }else{
                    mostrarProducto(pos);
                    System.out.println("\n¿Esta seguro que desea eliminar este producto? Si(1)  No(2)");
                    opPro = leer.nextInt();
                    if(opPro == 1){
                        for(int i=pos; i<=np-1; i++){
                            productos[i] = productos[i+1];
                        }
                        np = np - 1;
                        System.out.println("\nProducto eliminado");
                        pausa();
                    }
                }
                break;
            }
        }while(opCrud != 5);
    }
    public static void menuPrincipal(){
        System.out.println("--- Menú Principal ---");
        System.out.println("1. Productos");
        System.out.println("2. Clientes");
        System.out.println("3. Ventas");
        System.out.println("4. Salir");
    }
    public static void menuCrud(){
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Modificar");
        System.out.println("4. Eliminar");
        System.out.println("5. Regresar al menú principal");
    }
    public static void menuCud2(){
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Modificar");
        System.out.println("4. Regresar al menú principal");
    }
    public static int consultarProducto(String codproducto){
        int posicion = -1;
        for(int i=0; i<np; i++){
            if(productos[i].codigo.equals(codproducto)){
                posicion = i;
            }
        }
        return posicion;
    }
    public static int consultarCliente(String cedula){
        int posicion = -1;
        for(int i=0; i<nc; i++){
            if(clientes[i].nroDocumento.equals(cedula)){
                posicion = i;
            }
        }
        return posicion;
    }
    public static int consultarVenta(String factura){
        int posicion = -1;
        for(int i=0; i<nv; i++){
            if(ventas[i].nroRecibo.equals(factura)){
                posicion = i;
            }
        }
        return posicion;
    }
    public static void mostrarProducto(int posicion_prod){
        System.out.println("\n********************************");
        System.out.println("    Información del Producto    ");
        System.out.println("********************************");
        System.out.println("Codigo : " + productos[posicion_prod].codigo);
        System.out.println("Nombre : " + productos[posicion_prod].nombre);
        System.out.println("Precio : " + productos[posicion_prod].precio);
    }
    public static void mostrarCliente(int posicion_cli){
        System.out.println("\n*********************************");
        System.out.println("     Información del Cliente     ");
        System.out.println("*********************************");
        System.out.println("Documento : " + clientes[posicion_cli].nroDocumento);
        System.out.println("Apellido  : " + clientes[posicion_cli].apellido);
        System.out.println("Nombre    : " + clientes[posicion_cli].nombre);
        System.out.println("Celular   : " + clientes[posicion_cli].celular);
    }
    public static void mostrarVenta(int posicion_ven){
        System.out.println("\n*********************************");
        System.out.println("     Información de la venta     ");
        System.out.println("*********************************");
        System.out.println("Fecha       : " + ventas[posicion_ven].fecha);
        System.out.println("Nro. Recibo : " + ventas[posicion_ven].nroRecibo);
        
        System.out.println("Precio      : " + ventas[posicion_ven].precio);
    }
    public static void listarProductos(){
        System.out.println("        Listado de Productos        ");
        System.out.println("------------------------------------");
        System.out.println(" Codigo        Nombre        Precio ");
        System.out.println("------------------------------------");
        for(int i=0; i<np; i++){
            System.out.println(productos[i].codigo + "\t\t" + productos[i].nombre + "\t\t" + productos[i].precio);
        }
    }
    public static void listarClientes(){
        System.out.println("                   Listado de Clientes                   ");
        System.out.println("---------------------------------------------------------");
        System.out.println(" Documento        Apellidos        Nombre        Celular ");
        System.out.println("---------------------------------------------------------");
        for(int i=0; i<nc; i++){
            System.out.println(clientes[i].nroDocumento + "\t\t" + clientes[i].apellido + "\t\t" + clientes[i].nombre + "\t\t" + clientes[i].celular);
        }
    }
    public static void listarVentas(){
    }   
    public static void limpiarPantalla(){
        try{
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
            Process startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void pausa(){
        leer.nextLine();
        System.out.println("\t\nPresione enter para continuar...");
        leer.nextLine();
    }
}