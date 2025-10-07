import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RepositorioProcesos repo = new RepositorioProcesos();
        Planificador planificador = new Planificador(repo);
        VistaConsola vista = new VistaConsola();
        Controlador controlador = new Controlador(repo, planificador, vista);

        try (Scanner sc = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n--- Simulador de Procesos ---");
                System.out.println("1) Registrar Proceso");
                System.out.println("2) Listar Procesos");
                System.out.println("3) Ejecutar todos");
                System.out.println("4) Ejecutar proceso por PID");
                System.out.println("5) Buscar proceso por PID");
                System.out.println("6) Eliminar proceso por PID");
                System.out.println("0) Salir");
                System.out.print("Seleccione opción: ");

                String entrada = sc.nextLine();
                int opcion;
                try {
                    opcion = Integer.parseInt(entrada.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Opción inválida.");
                    continue;
                }

                switch (opcion) {
                    case 1 -> registrarProceso(sc, controlador);
                    case 2 -> System.out.println(controlador.listarProcesos());
                    case 3 -> System.out.println(controlador.ejecutarTodos());
                    case 4 -> ejecutarPorPID(sc, controlador);
                    case 5 -> buscarPorPID(sc, controlador);
                    case 6 -> eliminarPorPID(sc, controlador);
                    case 0 -> {
                        continuar = false;
                        System.out.println("Saliendo...");
                    }
                    default -> System.out.println("Opción no válida.");
                }
            }
        }
    }

    private static void registrarProceso(Scanner sc, Controlador controlador) {
        try {
            System.out.println("Tipo de proceso:");
            System.out.println("1) CPU\n2) IO\n3) Daemon\n4) Red\n5) GPU");
            System.out.print("Seleccione tipo: ");
            int tipo = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();

            Proceso p = switch (tipo) {
                case 1 -> {
                    System.out.print("Ciclos CPU: ");
                    yield new ProcesoCPU(nombre, Integer.parseInt(sc.nextLine().trim()));
                }
                case 2 -> {
                    System.out.print("Dispositivo: ");
                    yield new ProcesoIO(nombre, sc.nextLine().trim());
                }
                case 3 -> {
                    System.out.print("Intervalo en segundos: ");
                    yield new ProcesoDaemon(nombre, Duration.ofSeconds(Integer.parseInt(sc.nextLine().trim())));
                }
                case 4 -> {
                    System.out.print("Puerto: ");
                    yield new ProcesoRed(nombre, Integer.parseInt(sc.nextLine().trim()));
                }
                case 5 -> {
                    System.out.print("Memoria VRAM (MB): ");
                    yield new ProcesoGPU(nombre, Integer.parseInt(sc.nextLine().trim()));
                }
                default -> null;
            };

            if (p == null) System.out.println("Tipo no válido.");
            else System.out.println(controlador.registrarProceso(p));

        } catch (Exception e) {
            System.out.println("Error al registrar proceso: " + e.getMessage());
        }
    }

    private static void ejecutarPorPID(Scanner sc, Controlador c) {
        System.out.print("PID: ");
        try {
            int pid = Integer.parseInt(sc.nextLine().trim());
            System.out.println(c.ejecutarProceso(pid));
        } catch (NumberFormatException e) {
            System.out.println("PID inválido.");
        }
    }

    private static void buscarPorPID(Scanner sc, Controlador c) {
        System.out.print("PID: ");
        try {
            int pid = Integer.parseInt(sc.nextLine().trim());
            System.out.println(c.buscarProceso(pid));
        } catch (NumberFormatException e) {
            System.out.println("PID inválido.");
        }
    }

    private static void eliminarPorPID(Scanner sc, Controlador c) {
        System.out.print("PID: ");
        try {
            int pid = Integer.parseInt(sc.nextLine().trim());
            System.out.println(c.eliminarProceso(pid));
        } catch (NumberFormatException e) {
            System.out.println("PID inválido.");
        }
    }
}
