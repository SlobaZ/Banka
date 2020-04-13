var bankaApp = angular.module("bankaApp",["ngRoute"]);

bankaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/klijenti', {
			templateUrl : '/app/html/klijenti.html'
		})
		.when('/klijenti/add', {
			templateUrl : '/app/html/klijent-add.html'
		})
		.when('/klijenti/edit/:id', {
			templateUrl : '/app/html/klijent-edit.html'
		})
		.when('/nalozi', {
			templateUrl : '/app/html/nalozi.html'
		})
		.when('/nalozi/add', {
			templateUrl : '/app/html/nalog-add.html'
		})
		.when('/nalozi/edit/:id', {
			templateUrl : '/app/html/nalog-edit.html'
		})
		.when('/transakcije', {
			templateUrl : '/app/html/transakcije.html'
		})
		.when('/brzetransakcije', {
			templateUrl : '/app/html/brzetransakcije.html'
		})
		.when('/krediti', {
			templateUrl : '/app/html/krediti.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);



//  KLIJENTI

bankaApp.controller("KlijentiCtrl", function($scope, $http, $location, $window){
	
	var url = "/klijenti";
	
	$scope.klijenti = [];
	
	$scope.search = {};
	$scope.search.imeprezime = "";
	$scope.search.mesto = "";
	$scope.search.stanje = "";
		
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	
	var getKlijenti = function(){
		
		var config = {params:{}};
		
		if($scope.search.imeprezime != ""){
			config.params.imeprezime = $scope.search.imeprezime;
		}
		if($scope.search.mesto != ""){
			config.params.mesto = $scope.search.mesto;
		}
		if($scope.search.stanje != ""){
			config.params.stanje = $scope.search.stanje; 
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.klijenti = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch Klijenti");
			}
		);
	}
	
	getKlijenti();
	
	
	$scope.goToAdd = function(){
		$location.path("/klijenti/add" );
	}
	
		
	$scope.goToEdit = function(id){
		$location.path("/klijenti/edit/" + id);
	}
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getKlijenti(); 
				alert("Succes!");	
			},
			function error(){
				alert("Couldn't delete the Klijent")
			}
		);
		
	}
		
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getKlijenti();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getKlijenti();
	}
	
	
	
});


bankaApp.controller("EditKlijentCtrl", function($scope, $http, $routeParams, $location , $log , $window ){
	
	var klijentiUrl = "/klijenti/" + $routeParams.id;
		
	$scope.klijent = {};
	$scope.klijent.imeprezime = "";
	$scope.klijent.mesto = "";
	$scope.klijent.adresa = "";
	$scope.klijent.jmbg = "";	
	$scope.klijent.telefon = "";
	$scope.klijent.mobilni = "";
	$scope.klijent.brojracuna = "";
	$scope.klijent.stanje = "";
	
	var getKlijenti = function(){
		$http.get(klijentiUrl).then(
			function success(res){
				$scope.klijent = res.data;
			},
			function error(){
				alert("Couldn't fetch Klijent.");
			}
		);
	}
	
	getKlijenti();
	
	
	$scope.doEdit = function(){
		$http.put(klijentiUrl, $scope.klijent).then(
			function success(){
				$location.path("/klijenti");
			},
			function error(){
				alert("Niste dobro popunili podatke!"); 

			}
		);
	}
	
	
	
});



bankaApp.controller("AddKlijentCtrl", function($scope, $http, $routeParams, $location){
	
	var klijentiUrl = "/klijenti";
	
	$scope.newKlijent = {};
	$scope.newKlijent.imeprezime = "";
	$scope.newKlijent.mesto = "";
	$scope.newKlijent.adresa = "";
	$scope.newKlijent.jmbg = "";	
	$scope.newKlijent.telefon = "";
	$scope.newKlijent.mobilni = "";
	$scope.newKlijent.brojracuna = "";
	$scope.newKlijent.stanje = "";
	
	var getKlijenti = function(){
		$http.get(klijentiUrl).then(
			function success(res){
				$scope.klijent = res.data;
			},
			function error(){
				alert("Couldn't fetch Klijent.");
			}
		);
	}
	
	getKlijenti();
	
	$scope.doAdd = function(){
		$http.post(klijentiUrl, $scope.newKlijent).then(
			function success(res){
				$location.path("/klijenti");
			},
			function error(){
				alert("Couldn't fetch Klijent.");
			}
		);
	}
	
	
});


//  NALOZI

bankaApp.controller("NaloziCtrl", function($scope, $http, $location){
	
	var url = "/transakcije";
	
	$scope.transakcije = [];
	
	$scope.search = {};
	$scope.search.uplatilac = "";
	$scope.search.primalac = "";
	$scope.search.uplatilacMesto = "";
	$scope.search.primalacMesto = "";
	$scope.search.iznosMin = "";
	$scope.search.iznosMax = "";
	$scope.search.datumvremePocetak = "";
	$scope.search.datumvremeKraj = "";
		
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	var getNalozi = function(){
		
		var config = {params:{}};
		
		if($scope.search.uplatilac != ""){
			config.params.uplatilac = $scope.search.uplatilac;
		}
		if($scope.search.primalac != ""){
			config.params.primalac = $scope.search.primalac;
		}
		if($scope.search.uplatilacMesto != ""){
			config.params.uplatilacMesto = $scope.search.uplatilacMesto;
		}
		if($scope.search.primalacMesto != ""){
			config.params.primalacMesto = $scope.search.primalacMesto;
		}
		if($scope.search.iznosMin != ""){
			config.params.iznosMin = $scope.search.iznosMin; 
		}
		if($scope.search.iznosMax != ""){
			config.params.iznosMax = $scope.search.iznosMax; 
		}
		if($scope.search.datumvremePocetak != ""){
			config.params.datumvremePocetak = $scope.search.datumvremePocetak; 
		}
		if($scope.search.datumvremeKraj != ""){
			config.params.datumvremeKraj = $scope.search.datumvremeKraj; 
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.transakcije = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch nalozi");
			}
		);
	}
	
	getNalozi();
	
	$scope.goToEdit = function(id){
		$location.path("/nalozi/edit/" + id);
	}
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getNalozi(); 
				alert("Succes!");	
			},
			function error(){
				alert("Couldn't delete the Klijent")
			}
		);
		
	}
		
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getNalozi();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getNalozi();
	}
	
	
	
});


bankaApp.controller("EditNalogCtrl", function($scope, $http, $routeParams, $location){
	
	var naloziUrl = "/transakcije/" + $routeParams.id;
	var klijentiUrl = "/klijenti";
	
	$scope.transakcija = {};
	$scope.transakcija.uplatilacId = "";
	$scope.transakcija.primalacId = "";
	$scope.transakcija.iznos = "";
	$scope.transakcija.datetime = "";
	
	
	$scope.klijenti = [];
	
	var getKlijenti = function(){
		$http.get(klijentiUrl).then(
			function success(res){
				$scope.klijenti = res.data;
				getNalozi();
			},
			function error(){
				alert("Couldn't fetch Klijenti");
			}
	 	);
	}
	
	var getNalozi = function(){
		$http.get(naloziUrl).then(
			function success(res){
				$scope.transakcija = res.data;
			},
			function error(){
				alert("Couldn't fetch Nalog.");
			}
		);
	}
	
	getKlijenti();
	
	
	$scope.doEdit = function(){
		$http.put(naloziUrl, $scope.transakcija).then(
			function success(){
				$location.path("/nalozi");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
});



//  BRZE TRANSAKCIJE 

bankaApp.controller("BrzeTransakcijeCtrl", function($scope, $http, $routeParams, $location){
	
	var transakcijeUrl = "/transakcije";
	var klijentiUrl = "/klijenti";
	
	$scope.klijenti = [];
	
	$scope.NewBrzaTransakcija = {};
	$scope.NewBrzaTransakcija.uplatilacId = "";
	$scope.NewBrzaTransakcija.primalacId = "";
	$scope.NewBrzaTransakcija.iznos = "";

	
	var getKlijenti = function(){
		$http.get(klijentiUrl).then(
			function success(res){
				$scope.klijenti = res.data;
			},
			function error(){
				alert("Couldn't fetch Klijent.");
			}
		);
	}
	
	getKlijenti();
	
	$scope.doUplati = function(){
		$http.post(transakcijeUrl + "/uplati" , $scope.NewBrzaTransakcija).then(
			function success(res){
				$location.path("/nalozi");
				alert("Succes!");
				getKlijenti();
			},
			function error(){
				alert("Couldn't save the Transakcija");
			}
		);
	}
	
	
});


//TRANSAKCIJE 

bankaApp.controller("TransakcijeCtrl", function($scope, $http, $routeParams, $location){
	
	var transakcijeUrl = "/transakcije";
	var klijentiUrl = "/klijenti";
	
	$scope.klijenti = [];
	
	$scope.search = {};
	$scope.search.imeprezime = "";
	$scope.search.mesto = "";
	
	$scope.uloga = {};
	
	$scope.podatci = {};
	$scope.podatci.imeprezimeU = "";
	$scope.podatci.jmbgU = "";
	$scope.podatci.mestoU = "";
	$scope.podatci.brojracunaU = "";
	$scope.podatci.imeprezimeP = "";
	$scope.podatci.jmbgP = "";
	$scope.podatci.mestoP = "";
	$scope.podatci.brojracunaP = "";
	
	 var id = "";
	 var imeprezime = "";
     var jmbg = "";
     var mesto = "";
     var brojracuna = "";
     var stanje = "";
	
	$scope.NewTransakcija = {};
	$scope.NewTransakcija.uplatilacId = "";
	$scope.NewTransakcija.primalacId = "";
	$scope.NewTransakcija.iznos = "";

	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	var getKlijenti = function(){
		
		var config = {params:{}};
		
		if($scope.search.imeprezime != ""){
			config.params.imeprezime = $scope.search.imeprezime;
		}
		if($scope.search.mesto != ""){
			config.params.mesto = $scope.search.mesto;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(klijentiUrl, config);
		promise.then(
			function success(res){
				$scope.klijenti = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch Klijenti");
			}
		);
	}
	
	getKlijenti();
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getKlijenti();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getKlijenti();
	}
	
	 $scope.GetUloga = function (index) {
		 $scope.uloga.uplatilac = $scope.uloga[index].uplatilac;
		 $scope.uloga.primalac = $scope.uloga[index].primalac;
     };
     
	
	 $scope.GetDetails = function (index) {
		 
		  id = $scope.klijenti[index].id;
		  imeprezime = $scope.klijenti[index].imeprezime;
          jmbg = $scope.klijenti[index].jmbg;
          mesto = $scope.klijenti[index].mesto;
          brojracuna = $scope.klijenti[index].brojracuna;
          stanje = $scope.klijenti[index].stanje;
          
         if($scope.uloga == "uplatilac"){
        	 $scope.NewTransakcija.uplatilacId = id;
        	 $scope.podatci.imeprezimeU = " . " + imeprezime + " , ";
        	 $scope.podatci.jmbgU = " jmbg: " + jmbg  + " , ";
        	 $scope.podatci.mestoU = " Mesto: " + mesto  + " , ";
        	 $scope.podatci.brojracunaU = " Racun: " +  brojracuna ;
         }
         if($scope.uloga == "primalac"){
        	 $scope.NewTransakcija.primalacId = id;	 
        	 $scope.podatci.imeprezimeP =" . " + imeprezime + " , ";
        	 $scope.podatci.jmbgP = " jmbg: " + jmbg  + " , ";
        	 $scope.podatci.mestoP = " Mesto: " + mesto  + " , ";
        	 $scope.podatci.brojracunaP = " Racun: " +  brojracuna ;
         }
     };

	
	$scope.doUplati = function(){
		$http.post(transakcijeUrl + "/uplati" , $scope.NewTransakcija).then(
			function success(res){
				$location.path("/nalozi");
				alert("Succes!");
				getKlijenti();
			},
			function error(){
				alert("Couldn't save the Transakcija");
			}
		);
	}
	
	
	     
	
});



//  KREDITI 

bankaApp.controller("KreditiCtrl", function($scope, $http, $routeParams, $location){
	
	var klijentiUrl = "/klijenti";
	
	$scope.klijenti = [];
	
	$scope.search = {};
	$scope.search.imeprezime = "";
	$scope.search.mesto = "";
	
	$scope.klijent = {};
	$scope.klijent.id = "";
	$scope.klijent.imeprezime = "";
	$scope.klijent.jmbg = "";
	$scope.klijent.mesto = "";
	$scope.klijent.brojracuna = "";
	$scope.klijent.stanje = "";
	
	$scope.iznosKredita = "";
	$scope.trajanje = "";
	
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	var getKlijenti = function(){
		
		var config = {params:{}};
		
		if($scope.search.imeprezime != ""){
			config.params.imeprezime = $scope.search.imeprezime;
		}
		if($scope.search.mesto != ""){
			config.params.mesto = $scope.search.mesto;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(klijentiUrl, config);
		promise.then(
			function success(res){
				$scope.klijenti = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch Klijenti");
			}
		);
	}
	
	getKlijenti();
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getKlijenti();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getKlijenti();
	}
	 
	
	 $scope.GetDetails = function (index) {
		 
		var imeprezime = $scope.klijenti[index].imeprezime;
		var jmbg = $scope.klijenti[index].jmbg;
		var mesto = $scope.klijenti[index].mesto;
		var brojracuna = $scope.klijenti[index].brojracuna;
		var stanje = $scope.klijenti[index].stanje;
          
        $scope.klijent.id = $scope.klijenti[index].id;
        $scope.klijent.imeprezime = imeprezime;
        $scope.klijent.jmbg = " JMBG: " + jmbg  + " , ";
        $scope.klijent.mesto = " Mesto: " + mesto ;
        $scope.klijent.brojracuna = " Racun: " +  brojracuna  + " , ";
        $scope.klijent.stanje = " Stanje: " + stanje;
     };
     
	
     $scope.doKredit = function(id,iznosKredita,trajanje){
 		$http.post(klijentiUrl + "/" + id + "/" + iznosKredita + "/" + trajanje + "/uzmiKredit").then(
 				function success(res){
 					getKlijenti();
 					alert("Succes!");
 				},
 				function error(){
 					alert("Niste u mogucnosti da uzmete ovaj kredit!");
 				}	
 		);
 	}
	
	
});





