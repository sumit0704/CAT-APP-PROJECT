package com.catapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download_Internal_CServlet
 */
@WebServlet("/Download_Internal_CServlet")
public class Download_Internal_CServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download_Internal_CServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("\n  Download_Internal_C servlet: ");
		
		
		ArrayList<String> assay_1_pheno = new ArrayList<>(Arrays.asList(				
				"A375_CMFDA", 	"A375_ROS", 	"A375_CASP", 	"A375_PROT", 	"A375_ATP",  
				"A549_CMFDA", 	"A549_ROS", 	"A549_CASP", 	"A549_PROT", 	"A549_ATP",  
				"HepG2_CMFDA", 	"HepG2_ROS", 	"HepG2_CASP", 	"HepG2_PROT", 	"HepG2_ATP",  
				"HepaRG_CMFDA", "HepaRG_ROS", 	"HepaRG_CASP", 	"HepaRG_PROT", 	"HepaRG_ATP",  
				"MCF7_CMFDA", 	"MCF7_ROS", 	"MCF7_CASP", 	"MCF7_PROT", 	"MCF7_ATP",  
				"HT29_CMFDA", 	"HT29_ROS", 	"HT29_CASP", 	"HT29_PROT", 	"HT29_ATP",  
				"LN229_CMFDA", 	"LN229_ROS", 	"LN229_CASP", 	"LN229_PROT", 	"LN229_ATP",  
				"HEK10205f_CMFDA", "HEK10205f_ROS", "HEK10205f_CASP", "HEK10205f_PROT", "HEK10205f_ATP",  
				"HLMVEC_CMFDA", "HLMVEC_ROS", 	"HLMVEC_CASP", 	"HLMVEC_PROT", 	"HLMVEC_ATP",  
				"HMePC_CMFDA", 	"HMePC_ROS", 	"HMePC_CASP", 	"HMePC_PROT", 	"HMePC_ATP",  
				"SH-SY5Y_CMFDA", "SH-SY5Y_ROS", "SH-SY5Y_CASP", "SH-SY5Y_PROT", "SH-SY5Y_ATP",
		
				"HEP_CalceinAM", "HEP_LipidTOX", "ENDO_Cyto", "ENDO_CTG", "ENDO_TubForm", 
				"HUV_Cyto", "HUV_CTG", "HUV_TubForm", 
				"Neur_Neur", "Neur_CTG", 
				"Macro_CalceinAM", "Macro_Mito" ));
		
		
		ArrayList<String> selected_assay_1_pheno = new ArrayList<String>();
		for(int x = 0; x < 66; x = x + 1) {
	         // System.out.println("cell lines: "+ cell_lines.get(x));
	         String received_value = request.getParameter(assay_1_pheno.get(x));	// receiving the post value
	         if (received_value != null){
	        	 System.out.println("selected_assay_1_pheno: "+ received_value);
	        	 selected_assay_1_pheno.add(received_value);
	         }
	      }
		
		// System.out.println("selected_assay_1_pheno: "+ request.getParameter(selected_assay_1_pheno.get(1)));
		
		request.setAttribute("selected_assay_1_pheno", selected_assay_1_pheno);	// submit value to jsp page:
		
		
		
		
		
		ArrayList<String> all_phenos = new ArrayList<>(Arrays.asList(			
			"CM_Ca2_PF",  "CM_Ca2_PW",  "CM_Ca2_PW10", "CM_Ca2_PA", "CM_Ca2_PS", "CM_Ca2_PRT", "CM_Ca2_PDT",  
			"CM_Ca2_SEQ",  "CM_Hoechst_TC", "CM_Hoechst_NMA", "CM_Hoechst_SEQ",  "CM_Mito_posMT", "CM_Mito_MTmsa", 			
			"CM_Mito_ACMA", "CM_Mito_SEQ",  		
			
			"HEP_Hoechst_TC", 	"HEP_Hoechst_NI", "HEP_Hoechst_NMA", "HEP_Hoechst_SEQ",  "HEP_Mito_posMT",
			"HEP_Mito_MTmsa", "HEP_Mito_ACMA", "HEP_Mito_SEQ",  	
			
			"ENDO_Hoechst_TC", "ENDO_Hoechst_NI", 	"ENDO_Hoechst_NMA", "ENDO_Mito_SEQ",  "ENDO_Mito_MTmsa", 
			"ENDO_Mito_ACMA", "ENDO_Hoechst_SEQ",  	"ENDO_TubForm_TTL", "ENDO_TubForm_MTL", "ENDO_TubForm_TTA", 
			"ENDO_TubForm_BrPt", "ENDO_TubForm_SEQ",  		
			
			"HUV_Hoechst_TC", "HUV_Hoechst_NI", "HUV_Hoechst_NMA", "HUV_Hoechst_SEQ",  "HUV_Mito_posMT", 
			"HUV_Mito_MTmsa", "HUV_Mito_ACMA", "HUV_Mito_SEQ", "HUV_TubForm_TTL", "HUV_TubForm_MTL", 
			"HUV_TubForm_TTA", "HUV_TubForm_BrPt", "HUV_TubForm_SEQ",  	
			
			"Neur_Hoechst_TC", "Neur_Hoechst_NI", "Neur_Hoechst_NMA", "Neur_Hoechst_SEQ", "Neur_Mito_posMT", 
			"Neur_Mito_MTmsa", "Neur_Mito_ACMA", "Neur_Mito_SEQ", "Neur_NeurOut_TotOut", "Neur_NeurOut_MeOutCe", 
			"Neur_NeurOut_TotBra", "Neur_NeurOut_TotPr", "Neur_NeurOut_SEQ",  	
			
			"Macr_Hoechst_TC", "Macr_Hoechst_NI", "Macr_Hoechst_NMA", "Macr_Hoechst_SEQ",  "Macr_Mito_posMT", 	
			"Macr_Mito_MTmsa", "Macr_Mito_ACMA", "Macr_Mito_SEQ",  "Macr_Phag_PhagCe", "Macr_Phag_PhagAr", 
			"Macr_Phag_SEQ",  "Macr_Cyto_Il6", "Macr_Cyto_Il1a", "Macr_Cyto_SEQ",  "Macr_MacroOut_MIP1a", 
			"Macr_MacroOut_TNFa", "Macr_MacroOut_SEQ"));
		
		ArrayList<String> selected_phenos = new ArrayList<String>();
		for(int x = 0; x < 78; x = x + 1) {
	         // System.out.println("cell lines: "+ cell_lines.get(x));
	         String received_value = request.getParameter(all_phenos.get(x));	// receiving the post value
	         if (received_value != null){
	        	 System.out.println("selected_phenos: "+ received_value);
	        	 selected_phenos.add(received_value);
	         }
	      }
		
		request.setAttribute("selected_phenos", selected_phenos);	// submit value to jsp page:
	
		
		
		getServletContext().getRequestDispatcher("/WEB-INF/Download_Internal_C.jsp").forward(request, response);
	}

}