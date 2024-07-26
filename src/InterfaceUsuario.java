
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class InterfaceUsuario {
    protected Banco banco;
    protected Scanner scanner = new Scanner(System.in);

    public InterfaceUsuario(Banco banco) { 
        this.banco = banco;
    }

    public void iniciar() {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine().trim();
        Cliente cliente = new Cliente(nome);
        banco.adicionarCliente(cliente);
        System.out.println("\nSua conta foi criada com sucesso!\n");
        System.out.println("Olá " + nome + ", bem-vindo!\n");

        Conta conta = new ContaCorrente(cliente); 
        banco.adicionarConta(conta);

        System.out.println("==== Dados da conta =====");
        System.out.println("Número da Agência: " + conta.getAgencia());
        System.out.println("Número da Conta: " + conta.getNumero());


        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== Digite o número da operação =====\n");
            System.out.println("1 - Sacar");
            System.out.println("2 - Transferir");
            System.out.println("3 - Depositar");
            System.out.println("4 - Extrato");
            System.out.println("0 - Sair\n");

            try {
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        conta.sacar(0); 
                        System.out.println("Saldo atual: " + conta.nf.format(conta.getSaldo()));
                        break;
                    case 2:
                        conta.transferir(0, conta);
                        break;
                    case 3:
                        conta.depositar(0); 
                        System.out.println("Saldo atual: " + conta.nf.format(conta.getSaldo()));
                        break;
                    case 4: 
                        conta.imprimirInfos();
                        break;
                    case 0:
                        System.out.println("\nObrigado por utilizar nosso sistema. Volte sempre!");
                        break;
                    default:
                        System.out.println("\nOpção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }

        scanner.close();
    }
}