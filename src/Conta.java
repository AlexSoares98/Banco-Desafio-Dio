import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class Conta implements FuncaoConta {
    Scanner scanner = new Scanner(System.in);
    NumberFormat nf = NumberFormat.getCurrencyInstance();

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected Cliente cliente;
    protected int numero;
    protected double saldo;

    protected static List<Conta> contas = new ArrayList<>();

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++; 
        this.cliente = cliente;
        contas.add(this); 
    }

    @Override
    public void sacar(double valor) {
        while (saldo > 0) {
            try {
                System.out.print("Digite o valor que deseja sacar (Apenas números): ");
                valor = scanner.nextDouble();
                if (valor > 0 && valor <= saldo) {
                    saldo -= valor;
                    System.out.println("\nSaque no valor de " + nf.format(valor) + " realizado com sucesso!\n");
                    break;   
                } else {
                    System.out.println("\nValor de saque inválido, tente novamente!\n");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.next(); 
                scanner.nextLine(); 
            }
        }
    }

    @Override
    public void depositar(double valor) {        
        while (true) {
            try {
                System.out.print("Digite o valor que deseja depositar (Apenas números): ");
                valor = scanner.nextDouble();
                if (valor > 0) {
                    saldo += valor;
                    System.out.println("\nDepósito no valor de " + nf.format(valor) + " realizado com sucesso!\n");
                    break;   
                } else {
                    System.out.println("\nValor de depósito inválido, tente novamente!\n");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next(); 
                scanner.nextLine(); 
            }
        }
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        while (saldo > 0) {
            try {
                System.out.print("Digite o número da conta destino: ");
                int numeroContaDestino = scanner.nextInt();
                System.out.print("Digite a agência da conta destino: ");
                int agenciaContaDestino = scanner.nextInt();
                contaDestino = findConta(numeroContaDestino, agenciaContaDestino);
                if (contaDestino == null) {
                    System.out.println("\nConta destino não encontrada.");
                    System.out.println(" - Digite 1 para tentar novamente ");
                    System.out.println(" - Digite 0 para sair ");
                    int escolha = scanner.nextInt();
                    if (escolha == 0) {
                        System.out.println("Operação cancelada.");
                        break;
                    } else if (escolha == 1) {
                        continue;
                    } else {
                        System.out.println("Opção inválida, operação cancelada.");
                        break;
                    }
                }

                System.out.print("Digite o valor que deseja transferir (Apenas números): ");
                valor = scanner.nextDouble();
                if (valor <= 0) {
                    System.out.println("\nO valor da transferência deve ser positivo!");
                    break;
                } else if (this.numero == numeroContaDestino && this.agencia == agenciaContaDestino) {
                    System.out.println("\nNão é possível transferir para a própria conta.");
                    break;
                } else if (this.saldo >= valor) {
                    System.out.println("\nTransferência no valor de " + nf.format(valor) + " realizada com sucesso!\n");
                    this.saldo -= valor;
                    contaDestino.receberTransferencia(valor); 
                    break;
                } else {
                    System.out.println("Saldo insuficiente para transferência!");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.next();
                scanner.nextLine();
            }
        }
    }


    private void receberTransferencia(double valor) {
        this.saldo += valor;
    }

    Conta findConta(int numero, int agencia) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero && conta.getAgencia() == agencia) {
                return conta;
            }
        }
        return null; 
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfos() {
        System.out.println("\n===== Extrato =====\n");
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Agência: %d%n", this.agencia); 
        System.out.printf("Conta: %d%n", this.numero);
        System.out.printf("Saldo: %s%n", nf.format(this.saldo));
    }
}