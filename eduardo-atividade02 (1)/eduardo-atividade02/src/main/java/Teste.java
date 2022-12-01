import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Teste {


    public static void gravaArquivoCsv(ListaObj lista, String nomeArq) {
        FileWriter arq = null; // onjeto que representa arquivo de gravação
        Formatter saida = null; // objeto usado para gravar arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv"; // acrescenta a extensão csv ao nome do arquivo

        try {
            arq = new FileWriter(nomeArq); // segundo argumento boolean (true)
            saida = new Formatter(arq);
        } catch (IOException error) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Brinquedo b = (Brinquedo) lista.getElemento(i);
                saida.format("%d;%s;%.2f;%d;%s;%.2f\n", b.getId(), b.getNome(), b.getValorUnitario(), b.getQuantidadeEstoque(), b.getDescricao(), b.precoTotalEstoque());
            }
        } catch (FormatterClosedException error) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException error) {
                System.out.println("erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null; // objeto que representa o arquivo de leitura
        Scanner entrada = null; // objeto usada para ler o arquivo
        Boolean deuRuim = false;

        nomeArq += ".csv";

        //Bloco para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException error) {
            System.out.println("Arquivo não encontrado!");
            System.exit(1);
        }
        // bloco para ler o arquivo
        try {
            System.out.printf("%-10S %-20S %10S %10S %20S %20S\n", "código", "nome", "valor", "estoque", "descrição", "valor total no estoque");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                Double valor = entrada.nextDouble();
                int qtdEstoque = entrada.nextInt();
                String descricao = entrada.next();
                Double valorTotal = entrada.nextDouble();
                System.out.printf("%10d %-20s %10.2f %10d %20s %10.2f\n", id, nome, valor, qtdEstoque, descricao, valorTotal);
            }
        } catch (NoSuchElementException error) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException error) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException error) {
                System.out.println("erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }


    public static void main(String[] args) {
        Integer opcao = 0;
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNl = new Scanner(System.in);
        System.out.println("Bem vindo a lojinha Konosuba!");

        ListaObj<Brinquedo> brinquedosLoja = new ListaObj<>(10);

        do{
            System.out.println("Escolha a opção desejada: (digite 1, 2 ou 3)");
            System.out.println("1 - Adicionar um Brinquedo ao carrinho");
            System.out.println("2 - Exibir Relatório");
            System.out.println("3 - Sair");
            System.out.println("4 - Gravar CSV");
            System.out.println("5 - Ler CSV");
            opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    System.out.println("Digite o identificador:");
                    int id = leitor.nextInt();

                    System.out.println("Digite o nome do brinquedo:");
                    String nome = leitorNl.nextLine();

                    System.out.println("Agora, digite o valor do brinquedo:");
                    Double valor = leitor.nextDouble();

                    System.out.println("Qual a quantidade desse brinquedo em estoque?");
                    int qtdEstoque = leitor.nextInt();

                    System.out.println("Digite a descrição do brinquedo:");
                    String descricao = leitorNl.nextLine();

                    brinquedosLoja.adiciona(new Brinquedo(id,nome,valor,qtdEstoque, descricao));

                    System.out.println("Brinquedo adicionado com sucesso!");
                    break;
                case 2:
                    System.out.println("");
                    System.out.printf("%-6s %-14s %10s %-7s %-16s %-14s \n", "CÓDIGO", "BRINQUEDO", "VALOR", "ESTOQUE", "DESCRIÇÃO", "VALOR TOTAL NO ESTOQUE");
                    for (int i = 0; i < brinquedosLoja.getTamanho(); i++){
                        Brinquedo b = brinquedosLoja.getElemento(i);
                        System.out.printf("%06d %-14s %10.2f %7d %-16s %10.2f \n",
                                b.getId(),
                                b.getNome(),
                                b.getValorUnitario(),
                                b.getQuantidadeEstoque(),
                                b.getDescricao(),
                                b.precoTotalEstoque());
                    }
                    System.out.printf("");
                    break;
                case 3:
                    System.out.println("Obrigado pela compra! >_<");
                    break;
                case 4:
                    gravaArquivoCsv(brinquedosLoja, "Brinquedos");
                    System.out.println("Gravado com sucesso!");
                    break;
                case 5:
                    leExibeArquivoCsv("Brinquedos");
                    break;
                default:
                    System.out.println("Error com a opção digitada, verifique e tente novamente!");
                    break;
            }
        }while (opcao != 3);
    }
}
