import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeitorExcel {
	
	String endereco;

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	

	
	public ObservableList<Outorga> getListaOutorgas () {
		
		ObservableList<Outorga> obsListaOutorgas;
		
		obsListaOutorgas = FXCollections.observableArrayList();
		
		Outorga outorga;
		
		// leitor //
		FileInputStream arquivo = null;
				
			try {
				arquivo = new FileInputStream(new File(endereco));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 
	        // arquivo lido //
	        XSSFWorkbook workbook = null;
	        
						try {
							workbook = new XSSFWorkbook(arquivo);
						} catch (IOException e1) {
							
							e1.printStackTrace();
							System.out.println(" erro workbook " + e1);
						}
						
	        // tabela no excel //
	        XSSFSheet sheet = workbook.getSheet("ANÁLISE");
	        
	        Iterator<Row> rowIterator = sheet.iterator();
	            
	        while (rowIterator.hasNext()) { // iterar linhas
	        	
	            Row row = rowIterator.next();
	            
	            Iterator<Cell> cellIterator = row.cellIterator(); // iterar célula

		            outorga = new Outorga();
		            
		            String[] finalidades = new String [5];
		            int [] vazaoLH = new int [12];
		            int [] vazaoLD = new int [12];
		            int [] vazaoTC = new int [12];
		            
		            // preencher a outorga
		            while (cellIterator.hasNext()) { // célula
		            	
		               Cell cell = cellIterator.next();
		               
		               switch (cell.getColumnIndex()) {
		              
		               
		               case 0: // processo
		            	   
		            	   try {
		            		   outorga.setProcesso(cell.getStringCellValue());;}
		            	   catch (Exception e) 
		            	   		{ outorga.setProcesso(null);}
		            	   break;
		            	   
		               case 4: // tipo (registro, outorga, oficio
		            	   
		            	   try {
		            		   outorga.setTipo(cell.getStringCellValue());}
		            	   catch (Exception e) 
		            	   		{ outorga.setTipo(null);;}
		            	   break;
		            	   
		               case 8: // interessado
		            	   
		            	   try {
		            		   outorga.setInteressado(cell.getStringCellValue());}
		            	   catch (Exception e) 
		            	   		{ outorga.setInteressado(null);}
		            	   break;
		            	   
		               	case 9: // cpf cnpj
		            	   
		            	   try {
		            		   outorga.setCpfCNPJ(cell.getStringCellValue());}
		            	   catch (Exception e) 
		            	   		{ outorga.setCpfCNPJ(null);}
		            	   break;
		            	   
						case 10: // endereço
							   
							   try {
								   outorga.setEndereco(cell.getStringCellValue());}
							   catch (Exception e) 
							   		{ outorga.setEndereco(null);}
							   break;
							   
						case 12: // utm norte
							   
							   try {
								   outorga.setLat(Double.parseDouble(cell.getStringCellValue()));}
							   catch (Exception e) 
							   		{ outorga.setLat(null);}
							   break;
							   
						case 13: //  utm leste
							   
							   try {
								   outorga.setLng(Double.parseDouble(cell.getStringCellValue()));}
							   catch (Exception e) 
							   		{ outorga.setLng(null);}
							   break;
		            	   
		               case 14: // tipo poço 
		            	   
		            	   try {
		            		   outorga.setTipoPoco(cell.getStringCellValue());}
		            	   catch (Exception e) {outorga.setTipoPoco(null);}
		            	  
		            	   break;
		                    
		            	   
		               case 21:  // finalidade1
		            	   
		            	   finalidades [0]  = (String) cell.getStringCellValue();
		            	   break;
		            	   
		               case 25: // finalidade2
		            	   
		            	   finalidades [1]  = (String) cell.getStringCellValue();
		            	   break;  
		            	   
						case 29: // finalidade2
						            	   
		            	   finalidades [2]  = (String) cell.getStringCellValue();
		            	   break; 
						            	   
						case 33: // finalidade2
							   
						   finalidades [3]  = (String) cell.getStringCellValue();
						   break; 
							   
						case 37: // finalidade2
							   
						   finalidades [4]  = (String) cell.getStringCellValue();
						   outorga.setFinalidade(finalidades);
							   break; 
							   
						case 89: // bacia 
			            	   
			            	   try {
			            		   outorga.setBacia(cell.getStringCellValue());}
			            	   catch (Exception e) {
			            		   outorga.setBacia(null);}
			            	   break;
			            	   
						case 90: // uh unidade hidrográfica
			            	   
			            	   try {
			            		   outorga.setUh(cell.getStringCellValue());}
			            	   catch (Exception e) {
			            		   outorga.setUh(null);}
			            	   break;
		            	   
		               } // fim switch
		               
		               
		               // vazão litros por hora
		               if (cell.getColumnIndex() >= 41 && cell.getColumnIndex() <= 52) {
		            	   
		         	 	  
		         	 	  switch (cell.getCellType()) {
		         	 	  
		         	       case Cell.CELL_TYPE_NUMERIC:
		         	    	   
		         	           vazaoLH [cell.getColumnIndex() - 41 ] = (int) cell.getNumericCellValue();
		         	           break;
		         	           
		         	       case Cell.CELL_TYPE_STRING:
		         	
		         	           vazaoLH [cell.getColumnIndex() - 41 ] = 0;
		         	           break;
		         	 	  }
		         	 	  outorga.setVazaoHora(vazaoLH);
		         	   }
		               
		               // vazão litros por dia //
		               if (cell.getColumnIndex() >= 53 && cell.getColumnIndex() <= 64) {
		            	   
		          	 	  
		          	 	  switch (cell.getCellType()) {
		          	 	  
		          	       case Cell.CELL_TYPE_NUMERIC:
		          	    	   
		          	           vazaoLD [cell.getColumnIndex() - 53 ] = (int) cell.getNumericCellValue();
		          	           break;
		          	           
		          	       case Cell.CELL_TYPE_STRING:
		          	
		          	    	 vazaoLD [cell.getColumnIndex() - 53 ] = 0;
		          	           break;
		          	 	  }
		          	 	  outorga.setVazaoDia(vazaoLD);
		          	   }
		               
		               
		               // tempo de captação //
		               if (cell.getColumnIndex() >= 65 && cell.getColumnIndex() <= 76) {
		            	   
		          	 	  
		          	 	  switch (cell.getCellType()) {
		          	 	  
		          	       case Cell.CELL_TYPE_NUMERIC:
		          	    	   
		          	           vazaoTC [cell.getColumnIndex() - 65 ] = (int) cell.getNumericCellValue();
		          	           break;
		          	           
		          	       case Cell.CELL_TYPE_STRING:
		          	
		          	    	 vazaoTC [cell.getColumnIndex() - 65 ] = 0;
		          	           break;
		          	 	  }
		          	 	  outorga.setTempoCaptacao(vazaoTC);
		          	   }
		               
		              
		            } // fimsegundo while - células
		                  
			         try {
			        	 
						arquivo.close();
						
				         	} catch (IOException e) {
				         		e.printStackTrace(); 
				         		System.out.println("o aplicativo fechou errado");
				         		
							} // fim try
			         
			         
			         // para tirar as  linhas sem dados da tableview //
			         if (outorga.getInteressado() != null) {
			        	 obsListaOutorgas.add(outorga);
			         }
			       
			     } // fim primeiro while - linhas
		        
		return obsListaOutorgas;
		
	}
}
