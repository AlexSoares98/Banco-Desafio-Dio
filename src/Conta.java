import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Objects;

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
        if (saldo > 0) {
            valor = lerValor("Digite o valor que deseja sacar (Apenas números): ");
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                System.out.println("\nSaque no valor de " + nf.format(valor) + " realizado com sucesso!\n");
            } else {
                System.out.println("\nValor de saque inválido, tente novamente!\n");
            }
        }
    }
 
    @Override
    public void depositar(double valor) {
        valor = lerValor("Digite o valor que deseja depositar (Apenas números): ");
        if (valor > 0) {
            saldo += valor;
            System.out.println("\nDepósito no valor de " + nf.format(valor) + " realizado com sucesso!\n");
        } else {
            System.out.println("\nValor de depósito inválido, tente novamente!\n");
        }
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (saldo > 0) {
            int numeroContaDestino = lerNumero("Digite o número da conta destino: ");
            int agenciaContaDestino = lerNumero("Digite a agência da conta destino: ");
            
            if (this.numero == numeroContaDestino && this.agencia == agenciaContaDestino) {
                System.out.println("\nNão é possível transferir para a própria conta.");
                return;
            }

            contaDestino = findConta(numeroContaDestino, agenciaContaDestino);
            if (contaDestino == null) {
                System.out.println("\nConta destino não encontrada.");
                return;
            }

            valor = lerValor("Digite o valor que deseja transferir (Apenas números): ");
            if (valor <= 0) {
                System.out.println("O valor da transferência deve ser positivo!");
            } else if (this.saldo >= valor) {
                System.out.println("\nTransferência no valor de " + nf.format(valor) + " realizada com sucesso!\n");
                System.out.println("===== Destinatário =====");
                System.out.println("Titular: " + contaDestino.cliente.getNome());
                System.out.println("Conta: " + contaDestino.getNumero());
                System.out.println("Agência: " + contaDestino.getAgencia());
                this.saldo -= valor;
                contaDestino.receberTransferencia(valor);
            } else {
                System.out.println("Saldo insuficiente para transferência!");
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

    private double lerValor(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
                scanner.nextLine();
            }
        }
    }

    private int lerNumero(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
                scanner.nextLine();
            }
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numero == conta.numero && agencia == conta.agencia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, numero);
    }
}
