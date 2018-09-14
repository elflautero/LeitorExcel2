public class Outorga {

		private String interessado;
		private String tipoPoco;
		private String [] finalidade;
		private String processo;
		private String cpfCNPJ;
		private String bacia;
		private String uh;
		private Double lat;
		private Double lng;
		private String endereco;
		
		
		private int [] vazaoHora;
		private int [] vazaoDia;
		private int [] tempoCaptacao;
		
		private String tipo;
		
		
		// construtor padrão
		public Outorga () { }
        
		
		// construtor completo
	    public Outorga (
	    		String interessado, String tipoPoco, String [] finalidade, String processo, 
	    		String cpfCNPJ, String bacia, String uh, Double lat, Double lng,
	    		int [] vazaoHora, int [] vazaoDia, int [] tempoCaptacao, String endereco, String tipo) {
	    	
	        super();
	         
	        this.interessado = interessado;
	 		this.tipoPoco = tipoPoco;
	 		this.finalidade = finalidade;
	 		this.processo = processo;	 		
	 		this.cpfCNPJ = cpfCNPJ;
	 		this.bacia = bacia;
	 		this.uh = uh;
	 		this.lat = lat;
	 		this.lng = lng;
	 		
	 		this.vazaoHora = vazaoHora;
	 		this.vazaoDia = vazaoDia;
	 		this.tempoCaptacao = tempoCaptacao;
	 		this.endereco = endereco;
	 		this.tipo = tipo;
	 		
	     }
	    
	    
		public String getEndereco() {
			return endereco;
		}


		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}


		public String getInteressado() {
			return interessado;
		}

		public void setInteressado(String interessado) {
			this.interessado = interessado;
		}

		public String getTipoPoco() {
			return tipoPoco;
		}

		public void setTipoPoco(String tipoPoco) {
			this.tipoPoco = tipoPoco;
		}

		public String[] getFinalidade() {
			return finalidade;
		}

		public void setFinalidade(String[] finalidade) {
			this.finalidade = finalidade;
		}

		public String getProcesso() {
			return processo;
		}

		public void setProcesso(String processo) {
			this.processo = processo;
		}

		public String getCpfCNPJ() {
			return cpfCNPJ;
		}

		public void setCpfCNPJ(String cpfCNPJ) {
			this.cpfCNPJ = cpfCNPJ;
		}

		public String getBacia() {
			return bacia;
		}

		public void setBacia(String bacia) {
			this.bacia = bacia;
		}

		public String getUh() {
			return uh;
		}

		public void setUh(String uh) {
			this.uh = uh;
		}

		public Double getLat() {
			return lat;
		}

		public void setLat(Double lat) {
			this.lat = lat;
		}

		public Double getLng() {
			return lng;
		}

		public void setLng(Double lng) {
			this.lng = lng;
		}

		public int[] getVazaoHora() {
			return vazaoHora;
		}

		public void setVazaoHora(int[] vazaoHora) {
			this.vazaoHora = vazaoHora;
		}

		public int[] getVazaoDia() {
			return vazaoDia;
		}

		public void setVazaoDia(int[] vazaoDia) {
			this.vazaoDia = vazaoDia;
		}

		public int[] getTempoCaptacao() {
			return tempoCaptacao;
		}

		public void setTempoCaptacao(int[] tempoCaptacao) {
			this.tempoCaptacao = tempoCaptacao;
		}
		
		public String getTipo() {
			return tipo;
		}


		public void setTipo(String tipo) {
			this.tipo = tipo;
		}



	
	    
	    
}
