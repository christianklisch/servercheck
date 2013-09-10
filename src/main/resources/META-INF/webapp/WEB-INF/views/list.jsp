<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="cache-control" content="no-cache">
    <title>KServerControl</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/jquery-1.10.2.js"></script>	
	<script src="js/jquery.form.js"></script>
	<script src="js/kservercontrol.js"></script>
    <style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */

      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      .container {
        width: auto;
        max-width: 680px;
      }
      .container .credit {
        margin: 20px 0;
      }

    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">	
  </head>
  <body>
  	<div class="container">
	    <div class="page-header">
	    	<h1>KServerControl <small>Open Source</small></h1>
	    </div>
	   
	    
	    <p>${processes["r0"].command}</p>
	    
	    ${template}

    </div> 
    	

    <div id="footer">
      <div class="container">
         <p class="muted credit">Open Source ServerControl by <a href="http://www.christian-klisch.de">Christian Klisch</a>. Check for Updates on <a href="http://christianklisch.github.io/servercontrol">Github</a>.</p>
      </div>
    </div>   
   
  </body>
</html>