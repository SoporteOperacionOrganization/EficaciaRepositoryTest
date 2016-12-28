  $(function() {
	  
	  $('#RazonSocial').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			  if (!((key == 9) || (key == 13) || (key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) 
      					 || (key >= 65 && key <= 90) || (key ==110) || (key == 190))) {
      				  e.preventDefault();
      			  }
      		  }
	  });
	  $('#SoeidCaracteres').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey || e.shiftKey) {
			  e.preventDefault();
  		  } else {
  			  var key = e.keyCode;

  			  if (!((key == 9) || (key >= 48 && key <= 90) || (key >= 96 && key <= 105) || (key == 8))) {
  				  e.preventDefault();
  			  }
  			  }
  		  
	  });
	  
	  $('#Digitos, #Telefono').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey || e.shiftKey) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			  if (!((key == 9) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105) || (key == 8))) {
      				  e.preventDefault();
      			  }
      		  }
	  });
  
	  $('#Nombre, #Nombre1, #Ejecutivo').keydown(function(e) {
		  
		  if ( e.ctrlKey || e.altKey ) {
				  e.preventDefault();
      		  } else {
      			  var key = e.keyCode;
      			if (!((key == 9) || (key == 13) || (key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) 
     					 || (key >= 65 && key <= 90))){
      				  e.preventDefault();
      			  }
      		  }
	  });
	  
	  $('#fechaCierre').keydown(function(e) {
		  var key = e.keyCode;
		  if ( key >= 48) {
		        e.preventDefault();

		    } else if (key < 48) {

		        if (key != 9) {
		            e.preventDefault();
		        }
		    }
	  });
	  
	  $('#SoidCaracteres').keydown(function(e)  { 
          
          if ( e.ctrlKey || e.altKey || e.shiftKey) { 
                  e.preventDefault(); 
          } else { 
                  var key = e.keyCode;  

                  if (!((key == 9) || (key >= 48 && key <= 90) || (key >= 96 && key <= 105) || (key == 8))) { 
                          e.preventDefault(); 
                  } 
          } 
          
  });


	  	  
  });
  
  function formatoSoeid() { 
	  var m = document.getElementById("Soeid").value;
	  var expreg = /^[a-zA-Z]{2}([0-9]{5})*$/;
	  if(!expreg.test(m))
		  	alert("Soeid no cumple con el formato correcto"); 
	} 
