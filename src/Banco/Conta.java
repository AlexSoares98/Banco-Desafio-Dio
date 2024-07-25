package Banco;

public abstract class Conta implements FuncaoConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected Cliente cliente;
    protected int numero;
    protected double saldo;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++; 
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) {
        while (true) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                System.out.println("Saque no valor de R$" + valor + " realizado com sucesso!");
                break;   
            } else {      
                throw new IllegalArgumentException("Valor de saque inválido, tente novamente!");
            }
        }
    }

    @Override
    public void depositar(double valor) {        
        while (true) {
            if (valor > 0) {
                saldo += valor;
                System.out.println("Depósito no valor de R$" + valor + " realizado com sucesso!");
                break;   
            } else {      
                throw new IllegalArgumentException("Valor de saque inválido, tente novamente!");
            }
        }
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        while (true) {
            if(valor <= 0) {
                throw new IllegalArgumentException("O valor da trânsferência deve ser positivo!");
            }
            else if (this.saldo >= valor) {
                this.sacar(valor);
                contaDestino.depositar(valor);
                System.out.println("Trânsferência no valor de R$" + valor + " realizada com sucesso!");
                break; 
            } else {
                throw new IllegalArgumentException("Saldo insuficiente para transferência!"); 
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
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Agência: %d%n", this.agencia); 
        System.out.printf("Número: %d%n", this.numero);
        System.out.printf("Saldo: %.2f%n", this.saldo);
    }

}
