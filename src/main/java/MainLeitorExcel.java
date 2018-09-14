import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class MainLeitorExcel extends Application {
	
	TableView<Outorga> tvLista;
	
	// table collumn
	TableColumn<Outorga, String> tcProc; // = new TableColumn<Outorga, String>("Processo");
	TableColumn<Outorga, String> tcVaz; //  = new TableColumn<Outorga, String>("Vazão");
	TableColumn<Outorga, String> tcSis;//  = new TableColumn<Outorga, String>("Sistema");
	
	ObservableList<Outorga> obsList;
	
	LeitorExcel leitorExcel = new LeitorExcel();
	
	ComboBox<Outorga> cbOutorga;
	
	Boolean b = false;
	
	Button btnPlanilha;
	Button btnExcel;
	
	Pane pane;
	Pane pTabela;

	Outorga outorga;
	
	
	WebView webOutorgas;
	WebEngine engOutorga;
	
	String htmlRel;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		// -- inicitalizar o mapa -- //
		Platform.runLater(() ->{
			
			/*
			webOutorgas = new WebView();
			engOutorga = webOutorgas.getEngine();
			engOutorga.load(getClass().getResource("/outorgaSubterranea.html").toExternalForm()); 
			 // método de comunicação com o webEngine //
			  
			  */
		
		});
		
		
		try {
			
			btnPlanilha = new  Button("Planilha");
			btnPlanilha.setLayoutX(500);
			
			// ação do botão para receber o excel
			btnPlanilha.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			    	abrirTabela();
			    }
			});
			
			btnExcel = new Button("...");
			btnExcel.setLayoutX(560);
			 
			// ação do botão para receber o excel
			btnExcel.setOnAction(new EventHandler<ActionEvent>() {
				
			    public void handle(ActionEvent e) {
			    	
			    	
			    	
			    	try {
				    	// para escolher o arquivo  no computador
				    	FileChooser fileChooser = new FileChooser();
				        fileChooser.setTitle("Selecione a planilha");
				        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS", "*.xls", "*.xlsm","*.xlsx"));        
				        File file = fileChooser.showOpenDialog(null);
				        
				        abrirTabela();
				       
				        leitorExcel.setEndereco(file.toString());
				        
				        obsList = leitorExcel.getListaOutorgas();
				        tvLista.setItems(obsList);
				        
				        cbOutorga.setItems(obsList);
				        
				        /*
				        for (int i = 0; i< 40; i++) {
				        	System.out.println(obsList.get(i).getInteressado() + " e " + obsList.get(i).getLat());
				        }
				        */
				        
				        // converter o objeto outorga e uma string com o nome, tipo de outorga etc
				        cbOutorga.setConverter(new StringConverter<Outorga>() {
							
							public String toString(Outorga o) {
								
								return o.getTipo() + "  |  " + o.getInteressado() + "  |  " + o.getTipoPoco() + "  |  " + o.getEndereco() ;
							}
							
							public Outorga fromString(String string) {
								
								return null;
							}
						});
				        
				        
				        // capturar o usuário escolhido no bombo box
				        cbOutorga.valueProperty().addListener(new ChangeListener<Outorga>() {
				            
							@Override
							public void changed(ObservableValue<? extends Outorga> arg, Outorga oldOut, Outorga newOut) {

								webOutorgas = new WebView();
			        			engOutorga = webOutorgas.getEngine();
			        			
			        			String tipo = newOut.getTipo();
			        			
			        			switch (tipo) {
			        			
			        				case "OUTORGA": 
			        					engOutorga.load(getClass().getResource("/outorgaSubterranea.html").toExternalForm());
			        					break;
			        					
			        					
			        				case "REGISTRO": 
			        					engOutorga.load(getClass().getResource("/registroSubterranea.html").toExternalForm());
		        						break;
		        						
			        				case "OUTORGA PRÉVIA": 
			        					engOutorga.load(getClass().getResource("/outorgaPreviaSubterranea.html").toExternalForm());
		        						break;
		        						
			        				case "TRANSFERÊNCIA": 
			        					engOutorga.load(getClass().getResource("/outorgaTransferenciaSuterranea.html").toExternalForm());
		        						break;
		        						
			        				case "INDEFERIMENTO PRÉVIA": 
			        					engOutorga.load(getClass().getResource("/previaInderefimentoSubterranea.html").toExternalForm());
		        						break;
		        						
			        				case "INDEFERIMENTO OUTORGA": 
			        					engOutorga.load(getClass().getResource("/outorgaIndeferimentoSubterranea.html").toExternalForm());
		        						break;

			        			
			        			}
			        			
			        			if (newOut.getTipo().equals("OUTORGA")) {
			        				engOutorga.load(getClass().getResource("/outorgaSubterranea.html").toExternalForm());
			        			}
			        			else if (newOut.getTipo().equals("REGISTRO")) {
			        				engOutorga.load(getClass().getResource("/registroSubterranea.html").toExternalForm());
			        			} else {
			        				
			        			}
			        			
								engOutorga.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
						        {
						            public void changed(final ObservableValue<? extends Worker.State> observableValue,
						                                final Worker.State oldState,
						                                final Worker.State newState)
						            {
						                if (newState == Worker.State.SUCCEEDED)
						                {
						                  
						                	htmlRel = (String) engOutorga.executeScript("document.documentElement.outerHTML");
						                
						                	DocumentosOutorga docOut = new DocumentosOutorga();
									    	docOut.setHtmlRel(htmlRel);
									    	docOut.criarDocumento(newOut);
									    	
						                }
						               
						            }
						        });
								
							}
				        });
				          
				    	}
			    	
			    		catch (Exception ex) {
			    			System.out.println("fechou sem escolher o arquivo excel " + ex);
			    		} // fim do try catch
			    	
			    }
			    
			    
		    	
			}); // fim do evento btnExcel
			
			cbOutorga = new ComboBox<>();
			
			cbOutorga.setLayoutX(660);
			cbOutorga.setMinWidth(400);
			cbOutorga.setMaxWidth(400);
			
			pane = new Pane();
			pane.setStyle("-fx-background-color: #20B3FC;");
			pane.setMaxSize(300,300);
			
			pane.getChildren().addAll(btnPlanilha, btnExcel, cbOutorga);
			
			Scene scene = new Scene(pane,1200,768);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("exceção de chamada do  pane principal");
		}
	}
	
	// MÉTODO MAIN //
	public static void main(String[] args)  {
		
		launch(args);
		
	}
	
	public void abrirTabela () {
		
		System.out.println("abrir tabela inicializado");
		
		// table view //
		tvLista = new  TableView<Outorga>();
		
		// criar colunas e dar o nome a cada
		tcProc = new TableColumn<Outorga, String>("Interessado");
		tcVaz = new TableColumn<Outorga, String>("Tipo de Poço");
		tcSis = new TableColumn<Outorga,String>("Finalidade");
		
		// min width da table column
		tcProc.setMinWidth(450);
		tcVaz.setMinWidth(200);
		tcSis.setMinWidth(300);
		
		tcProc.setCellValueFactory(new PropertyValueFactory<Outorga, String>("interessado"));
		tcVaz.setCellValueFactory(new PropertyValueFactory<Outorga, String>("tipoPoco"));
		
		tcSis.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getFinalidade()[0]));
		
		//tvLista.setEditable(true);
		tvLista.getColumns().addAll(tcProc, tcVaz, tcSis);
		//obsList = FXCollections.observableArrayList();
		
		//pTabela.getChildren().add(tvLista);
		
		tvLista.setPrefSize(1100, 568);
		
		// capturar a outorga selecionada //
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
				Outorga outorga = (Outorga) newValue;
				
				System.out.println(outorga.getInteressado());

				for (int i = 0; i<12; i++) {
		        	 
		           	 System.out.println("mês " + (i + 1) + " vazão L/H " + outorga.getVazaoHora()[i] + " e litros dia " + outorga.getVazaoDia()[i] 
		           			 + " e tempo " + outorga.getTempoCaptacao()[i]);
		           	 
		            }
			}
		});
		
		tvLista.setLayoutY(30);
		//pTabela.getChildren().addAll(tvLista);
		
	    tvLista.setItems(obsList);
		
			Scene scene = new Scene(tvLista);
			Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
			stage.setWidth(1100);
			stage.setHeight(700);
		    stage.setScene(scene);
		    stage.setMaximized(false);
		    stage.setResizable(false);
		    //stage.setX(1030.0);
		    //stage.setY(550.0);
		   
		    //stage.setAlwaysOnTop(true); 
		    stage.show();
		    
		   
	} // fim método abriTabela
}


/*
 * capturar a tag para  adicionar a tabela
 
var x = document.getElementsByTagName('iframe')
var y = x[1].contentDocument;
y.body.getElementsByTagName('aaa')["0"].textContent = "vida louca"

y.body.getElementsByTagName('aaa')["0"].innerHTML = "<table border='1'><tbody><tr><td>eita nóis</td></tr</tbody></table>";

 */


