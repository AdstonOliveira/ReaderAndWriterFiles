import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EscreverArquivo {

   JFileChooser chooser = new JFileChooser();
   int returnVal;
   String arqLeitura = ""; // Armazena Arquivo escolhido
   String arqSalvo = ""; // Onde será salvo o novo
   
   
   public void escolheArquivo() throws FileNotFoundException, IOException{
      
         FileFilter filter = new FileNameExtensionFilter("Só TXT ou DOC carai...","txt","doc"); // Parametro de Filtro
         chooser.addChoosableFileFilter(filter); // Aplica filtro
      
         returnVal = chooser.showOpenDialog(chooser); // Armazena retorno da janela de seleção
         
         if (returnVal == JFileChooser.APPROVE_OPTION) { // Se selecionado OK salva o caminho absoluto do arquivo
            arqLeitura = chooser.getSelectedFile().getAbsolutePath();
         }
         
         BufferedWriter buffWrite;
      try (BufferedReader buffRead = new BufferedReader(new FileReader(arqLeitura)) ) {// Após ter escolhido o arquivo armazena no buffer
         this.novoArquivo(); // Abre selecao de local e nome para salvar
         buffWrite = new BufferedWriter(new FileWriter(arqSalvo)); // salvo
         String linhaBuff = buffRead.readLine();
         List <String> tmp = new ArrayList<>();
         
         while(linhaBuff != null){
            tmp.add(linhaBuff);
            linhaBuff = buffRead.readLine();
         }
         buffRead.close();
         
            for(int i = tmp.size(); i > 0; i--) // escreve de forma inversa o arquivo
            buffWrite.append(tmp.get(i-1) + "NOVO\n");
      } // Abre selecao de local e nome para salvar
         buffWrite.close();
         
         
   }

   public void novoArquivo(){
      JOptionPane.showMessageDialog(null, "Escolha o local para o novo");
      
      returnVal = chooser.showSaveDialog(chooser);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.arqSalvo = chooser.getSelectedFile().getAbsolutePath();
         }
   }

   public static void main(String args[]) throws IOException{
      
      EscreverArquivo ea = new EscreverArquivo();
      ea.escolheArquivo();
   }
   
}
   
   
