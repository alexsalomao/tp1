

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TP1 {

    private static MyCommand interC;
    static final int MAX_ALUNOS = 35;
    private static int alunosLidos=0;
    private static int notaMax = 0;
    private static int notaMin = 0;
    private static int notaAvg = 0;

    private static String[] nomeAlunos = new String[MAX_ALUNOS];
    private static int[] notasAlunos = new int[MAX_ALUNOS];

    public static void main(String[] args) {
        boolean querSair=false;

        interC=new MyCommand();

        do {
            interC.limparEcra();
            interC.showPrompt();
            String[] cmdEscrito = interC.lerComando();
            ArrayList<String> cmd = interC.validarComando(cmdEscrito);

            if (cmd == null) {
                interC.showMsg("Comando inválido. Digite help para ajuda");

            } else {
                if  ( cmd.get(0).equalsIgnoreCase("carregar") ) {
                    alunosLidos = loadData(nomeAlunos, "turmaLeit.txt");
                    int notA = loadData(notasAlunos);
                    if ( alunosLidos != notA ) {
                        System.out.println("alunos = " + alunosLidos);
                        System.out.println("notaA = " + notA);
                        interC.showMsg("Erro carregando dados");
                    }
                        
                    else

                        interC.showMsg("Dados carregados OK!");
                } else if (cmd.get(0).equalsIgnoreCase("listar") ) {
                    mostrarAlunos();

                } else if (cmd.get(0).equalsIgnoreCase("paginar") ) {
                    String input = JOptionPane.showInputDialog("Nũmeros estudantes por pãgina :");
                    int numeroU = Integer.parseInt(input);
                    mostrarAlunos(numeroU);

                } else if (cmd.get(0).equalsIgnoreCase("mostrarp") ) {
                    mostrarPauta();

                } else if (cmd.get(0).equalsIgnoreCase("mostrarr") ) {
                    mostraResumo();

                } else if (cmd.get(0).equalsIgnoreCase("top") ) {
                    mostrarTop();

                } else if (cmd.get(0).equalsIgnoreCase("pesquisarnome") ) {
                    String nomePesq = JOptionPane.showInputDialog("O que procura  :");
                    pesquisar(nomePesq);

                } else if (cmd.get(0).equalsIgnoreCase("pesquisarnota") ) {
                    String vaPesq = JOptionPane.showInputDialog("O que procura  :");
                    int notaPesq = Integer.parseInt(vaPesq);
                    pesquisar(notaPesq);
                } else if (cmd.get(0).equalsIgnoreCase("help") ) {
                    interC.showHelp();

                } else if (cmd.get(0).equalsIgnoreCase("terminar") ) {
                    querSair = true;
                }
            }

        } while (!querSair);

    }

    /**
     * Método implementado por Prof. Não devem alterar. Este método recebe
     * como parâmetros um array e um ficheiro
     * Lẽ cada linha do ficheiro e guarda no array. Retorna o número
     * de linhas que forma lidas do ficheiro.
     * @param lAlunos
     * @param nomeFicheiro
     * @return quantos nomes foram lidos do ficheiro -1 se não possível ler ficheiro
     */
    public static int loadData(String[] lAlunos, String nomeFicheiro) {
        Scanner in = null;
        File inputFile = new File(nomeFicheiro);
        //PrintWriter out = new PrintWriter(outputFileName);
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (in.hasNextLine()) {
            String nomeAl = in.nextLine();
            if ( (nomeAl != null) && !(nomeAl.isBlank()) && !(nomeAl.isEmpty() ) ) {
                lAlunos[i] = nomeAl;
                i++;
            }

        }
        in.close();
        return i;
    }

    /**
     * Método implementado por Prof. Não devem alterar. Este método recebe
     * como parâmetros um array de inteiros e vai gerar aleatoriamente valores inteiros entre
     * 0 e 20 que representam a nota de cada aluno.
     * @param lNotas
     * @return how much name was read from the files -1 if was not able to read the file
     */
    public static int loadData(int[] lNotas) {
        Random rand = new Random();
        int cont = 0;
        for (cont=0; cont < alunosLidos; cont++) {
            int randomNum = rand.nextInt(20) + 1;
            notasAlunos[cont] = randomNum;
        }
        return cont;
    }

    /**
     * Método a ser implementando no TP1.
     * O método deverá listar todos os nomes dos alunos guardados no array nomesAlunos.
     * O método deverá verificar se já foi carregado os dados para o array. Se sim mostra os
     * nomes dos alunos. Senão deve mostrar a mensagem "Não há dados"
     * @param
     * @return
     */
    public static void mostrarAlunos() {
            
        int num=0;//identifica alunos pelo numero

        if(nomeAlunos[0]!=null){
            //pega um aluno em cada ciclo
            for (String nome : nomeAlunos) {
                if(nome==null)
                    break;
                ++num;
                System.out.println(" \n " +num + nome);
               
            }
            interC.showMsg("");//enter para continuar
            
        }
        else{
            interC.showMsg("Nao ha dados");
        }






    }






    /**
     * Método a ser implementando no TP1
     * O método deverá listar todos os nomes dos alunos guardados no array nomesAlunos.
     * O método deverá verificar se já foi carregado os dados para o array. Se sim mostra os
     * nomes dos alunos. Senão deve mostrar a mensagem "Não há dados".
     * Neste método os dados não são mostrados todos de uma só vez. Devem ser apresentados até encher a tela.
     * Vamos supor que 10 nomes enchem a tela. Então deverá ser apresentados de 10 em 10. Esse número
     * que indica quantos nomes enchem a tela é um parâmetro do método.
     * @param tela é um inteiro que indica quantos alunos são mostrados.
     */
    public static void mostrarAlunos(int tela) {
        
        if(nomeAlunos[0]!=null )//string nomeAlunos tem ser diferente null
        {
            if(tela>0){
             
            int num=0;
            int pagina=1;// identificar pagina

            System.out.println("Pagina "+pagina+"");
            for (String nome : nomeAlunos) {

                if(nome==null)
                    break;

                ++num;
                System.out.println(num+""+nome);
                
                if(num>=tela){
                    interC.showMsg("\n Digite enter para proxima pagina");
                    System.out.println("\n");
                    ++pagina;
                    System.out.println(" pagina"+pagina+"");
                    num=0;
                }

            }

            interC.showMsg("");//enter para continuar
        }
         
         else
         interC.showMsg("numero introduzido invalido");   
        }
        else{
            interC.showMsg("Nao ha dados");
        }

        




        }


    /**
     * Método a ser implementando no TP1.
     * O método deverá percorrer o array de notas, calcular o valor da média aritmética de notas, a nota máximo e
     * a nota mínima.
     * Os valores calculados devem ser guaraddos na variáveis notaAVG (média),
     * notaMax (nota máxima) e notaMin(nota mínima)
     * Devem validar se o array de notas tem elementos. Se estiver vazio devem somente apresentar
     * a mensagem "Não há dados"
     */
    private static void calcularMaxMinAvg() {
       
        
        int k=0;
        int l=1;
        //Função para determinar a notaMaxima do Array
        for (k=0; k<31 ; k++){
            if(nomeAlunos[0]==null){
                System.out.println("Não ha dados");
                break;
            }
            else
                if(notasAlunos[k] > notaMax){
                notaMax=notasAlunos[k];
                }
        }
        notaMin = notasAlunos[0];
        for(k=0 ; k<31; k++){
            if(nomeAlunos[k]==null){
                System.out.println("Não ha dados");
                break;
            }
                
                    if(notasAlunos[k]<notaMin) {
                        notaMin=notasAlunos[k];
                    }
               // 
        }
        k++;
        System.out.println("Total dos Alunos: " +k);
        System.out.println("nota Maxima: \n  " +nomeAlunos[k]+notaMax);
        System.out.println("Nota Minima: \n  " +nomeAlunos[k]+notaMin);

        notaAvg=(notaMax+notaMin)/2;
        
        for(k=0 ; k<31 ; k++){
            if(notasAlunos[k] >= notaAvg){
                System.out.println(" \n " +nomeAlunos[k]+notasAlunos[k]);
                k++;}
        }
        System.out.println(" Notas Acima Média = " +l+ "aluno(s)");










    }

    /**
     * Método a ser implementando no TP1.
     * O método deverá apresentar um resumo da avaliação;
     * Nota máxima, Nota mínima, Nota média. Número de alunos com nota superior a média e número de alunos com
     * nota inferior a média.
     * a mensagem "Não há dados"
     */
    public static void mostraResumo() {
        
        if(nomeAlunos[0]!=null){
            
           //quantidade alunos com nota superior e inferior que a media:
            int notasuperior=0;
            int notasinferior=0;
            for(int x=0; nomeAlunos[x]!=null; ++x){ 
                    if(notasAlunos[x]>notaAvg)        
                        ++notasuperior;

                    if(notasAlunos[x]<notaAvg)
                        ++notasinferior;
            }
            calcularMaxMinAvg();
            System.out.println("Nota maxima: "+notaMax);
            System.out.println("Nota minima: "+notaMin);
            System.out.println("\nResumo:");
            System.out.println("Nota media: "+notaAvg);


            System.out.println("Numero de alunos com nota superior a media: "+notasuperior);
            System.out.println("Numero de alunos com nota inferior a media: "+notasinferior);
            interC.showMsg("");
        }
        else
            interC.showMsg("Nao ha dados");



    }
        



    /**
     * Método a ser implementando no TP1.
     * O método deverá apresentar o nome dos três alunos que têm as melhores notas.
     */
    public static void mostrarTop() {
      
        if(nomeAlunos[0]!=null){
            int maior = 20;
            int contar=0;
            int numeroAlunos=3;
            System.out.println(" os tres alunos com as melhores notas sao :");
     
            while(numeroAlunos>0){//percorrer todo as notas
                if(contar>=notasAlunos.length)
                {
                    contar=0;
                    --maior; //tira um ponto               
                }

                if(notasAlunos[contar]==maior){
                    System.out.println(nomeAlunos[contar]+""+notasAlunos[contar]);
                    --numeroAlunos;//tira um aluno
                } 
                
                ++contar;
            }

            interC.showMsg("");
        }    
        else{
            interC.showMsg("Nao ha dados");
        }

    }
    /**
     * Método a ser implementando no TP1.
     * Apresentar a pauta com nomes dos alunos e á frente cada nome a respectiva nota obtida.
     */
    public static void mostrarPauta() {
     

        int num=0;

        if(nomeAlunos[0]!=null){
            System.out.println("Num    Nome    nota");
            //pega o nome de cada aluno na string nomeAlunos 
            for (String nome : nomeAlunos) {
                if(nome==null)
                    break;
                ++num;
                
                System.out.println(num+"  "+nome+"  "+notasAlunos[num]);
                
                if(num==10)
                {
                    interC.showMsg("");
                    num=0;
                }
            }
            interC.showMsg("");
            
        }
        else{
            interC.showMsg("Nao ha dados");
        }

    }
    /**
     * Método a ser implementando no TP1
     * Apresentar para um aluno específico em que o nome é dado como parâmetro a nota de avaliação
     * @param nome é uma string contendo o nome do aluno que queremos apresentar a sua nota
     * @return
     */
    public static void mostrarDetalhesAluno(String nome) {
        interC.showMsg("A ser implementado ...");


    }

    /**
     * Método a ser implementando no TP1
     * O método deverá pedir um nome e pesquisar o array de nomes. Caso existir ou caso existem nomes
     * parecidos apresentar a lista de nomes. Nomes parecidos são nomes que iniciam com as mesmas duas ou três
     * primeiras letras. Ou apelidos iguais.
     */
    public static void pesquisar(String nome) {
        //mostrarDetalhesAluno(nome);

        if(nomeAlunos[0]!=null)
        {

            boolean achei=false;
            System.out.println("\n"); 

            for (String nomes : nomeAlunos)
            {
               
                if(nomes!=null && nome.length() >3)
                { 
                    
                    
                    String[] divisao = nomes.split(" ");
                    
                    
                
                    if(nome.equalsIgnoreCase(divisao[0])|| nome.equalsIgnoreCase(divisao[divisao.length -1]) )
                    {
                        System.out.println(""+nomes);
                        achei=true;
                    }

                    
                    else
                    {
                        if(nome.substring(0,3).equalsIgnoreCase(divisao[0].substring(0,3)) || nome.substring(0,3).equalsIgnoreCase(divisao[divisao.length -1].substring(0,3)))
                        {
                            System.out.println("Nome parecido com "+nome+": "+nomes);
                            achei=true;
                        }
                    }
                } 
            }
            
            if(!achei)
                interC.showMsg("Nao achei");
            else
                interC.showMsg("");
        }

        else
            interC.showMsg("Nao ha dados");



    }
         

    /**
     * Método a ser implementando no TP1
     * O método deverá pedir um nome e pesquisar o array de nomes. Caso existir ou caso existem nomes
     * parecidos apresentar a lista de nomes. Nomes parecidos são nomes que iniciam com as mesmas duas ou três
     * primeiras letras. Ou apelidos iguais.
     */
    public static void pesquisar(int nota) 
    {
       // interC.showMsg("A ser implementado ...");
            
            if(nomeAlunos[0]==null)
            {

                System.out.println("nao há dados ");
            }
            else 
            {
                for (int z=0 ;z<=notasAlunos.length-1;z++ )
                {

                    if(nomeAlunos[z]!=null && nota==notasAlunos[z])
                    {
                        System.out.println(" "+nomeAlunos[z]+" "+notasAlunos[z]);
                    }
                    
                }

            }


                    
            interC.showMsg("");//enter para continuar
            
        
    }
        

    

    private String[] searchByName(String nome) {
        return null;
    }
}


