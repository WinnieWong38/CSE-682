var categoryTable;
var chart;
var button = false;
var buttonTotal = false;
var buttonModal = true;
var categories = [];
var modalCategory;

$(document).ready(function() {
	init();
});

function init(){
	createChart();
	createTable();
	enableListeners();
	getTotalLimit();
	getUser();
}


function createChart(){

	var categoryArr = [];
	$.ajax({
			url: "/api/category/getCategories"
	}).done(function(data){
		for(let item in data){
			categories.push(data[item].category);
			$.ajax({
				url: "/api/limit/getLimitByCategory/" + data[item].categoryid,
				async: false
			}).done(function(limit){
				categoryArr.push([ data[item].category, limit.limit ? limit.limit : 0]);
				if(data.length === categoryArr.length){
					chart = c3.generate({
						bindto: "#c3chart_category",
						data: {
							columns: categoryArr,
							type: 'donut',
						},
						donut: {
							label: {
								show: false
							}
						}
					});
				};
			});
		}
		});
    
    }
		
	function createTable(){	
		$('#example thead tr').clone(true).appendTo( '#example thead' );
		$('#example thead tr:eq(1) th').each( function (i) {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
 
        $( 'input', this ).on( 'keyup change', function () {
            if ( categoryTable.column(i).search() !== this.value ) {
                categoryTable
                    .column(i)
                    .search( this.value )
                    .draw();
            }
        } );
		} );
		
		categoryTable = $('#example').DataTable({
			"columnDefs": [
				{
					"targets": [ 0 ],
					"visible": false,
					"searchable": false
				}
			]
		});

		$('#example tbody').on('click', 'tr', function () {
			createModal(categoryTable.row( this ).data(), $(this));
		} );
		
		$.ajax({
			url: "/api/category/getCategories"
		}).done(function(data){
			for(let item in data){
                var limit = '';
                $.ajax({
                    url: "/api/limit/getLimitByCategory/" + data[item].categoryid
                }).done(function(limit){
                    if(limit.limit){
                        limit = limit.limit;
                    }
                    categoryTable.row.add([
                        data[item].categoryid,
                        data[item].category,
                        limit
                    ]).draw( false );
                })
			}
		});
	}

	function getTotalLimit(){
		$.ajax({
			url: "/api/limit/getTotalLimit"
		}).done(function(limit){
			$('#totalLimitText').text(limit.limit);
		});
	}
		
		function enableListeners(){
			//Add new expense
			$('#submit').off('click').on('click', function(){
				if(button){
					var category = $('#category').val();
					var limitVal = $('#limit').val();
					$('#category').val('');
					$('#limit').val('');
					button = false;
					$('#submit').addClass('disabled');
					$.ajax({
						url: "/api/category/addCategory",
						method: "POST",
						contentType: "application/json",
						dataType: "json",
						data: JSON.stringify(category)
					}).done(function(category){
						categories.push(category.category)
						if(limitVal){
						var limit = createLimit(limitVal, category, false);
						$.ajax({
							url: "/api/limit/addLimit",
							method: "POST",
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(limit)
						}).done(function(limit){
							addToTable(category.categoryid, category.category, limit.limit);
						});
					}
					else{
						addToTable(category.categoryid, category.category, '')
					}
						
					});
				}
				
				});
				$('#submitTotal').off('click').on('click', function(){
					if(buttonTotal){
						var limit = createLimit($('#totalLimit').val(), null, true);
						buttonTotal = false;
						$('#submitTotal').addClass('disabled');
						$('#totalLimit').val('');
						$.ajax({
							url: "/api/limit/setTotalLimit",
							method: "POST",
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(limit)
						}).done(function(limit){
							$('#totalLimitText').text(limit.limit);
						});
				}
			});

			$('#submit').addClass('disabled');
			$('#categoryCardBody').off('change input').on('change input', function(){
				if(!isBlank($('#category').val())){
					if(categories.indexOf($('#category').val()) != -1){
						$('#submit').addClass('disabled');
						button = false;
						$('#duplicateText').text('Categories can not have the same name');
					}
					else{
						$('#submit').removeClass('disabled');
						button = true;
						$('#duplicateText').text('');
					}
				}
				else{
					$('#submit').addClass('disabled');
					button = false;
				}
			});

			$('#submitTotal').addClass('disabled');
			$('#totalCardBody').off('change input').on('change input', function(){
				if(!isBlank($('#totalLimit').val())){
					$('#submitTotal').removeClass('disabled');
					buttonTotal = true;
				}
				else{
					$('#submitTotal').addClass('disabled');
					buttonTotal = false;
				}
			});

			$('#categoryModal').off('change input').on('change input', function(){
				if(!isBlank($('#itemModal').val())){
					if(categories.indexOf($('#itemModal').val()) != -1 && modalCategory !== ($('#itemModal').val())){
						$('#submitModal').addClass('disabled');
						buttonModal = false;
						$('#duplicateTextModal').text('Categories can not have the same name');
					}
					else{
						$('#submitModal').removeClass('disabled');
						buttonModal = true;
						$('#duplicateTextModal').text('');
					}
				}
				else{
					$('#submitModal').addClass('disabled');
					buttonModal = false;
				}
			});

			$('#limit').off('input').on('input', function(){
				if($('#limit').val() < 0){
					$('#limit').val(0);
				}
			});

			$('#totalLimit').off('input').on('input', function(){
				if($('#totalLimit').val() < 0){
					$('#totalLimit').val(0);
				}
			});
		
			$('#limitModal').off('input').on('input', function(){
				if($('#limitModal').val() < 0){
					$('#limitModal').val(0);
				}
			});
		}

        function addToTable(categoryid, category, limit){
            categoryTable.row.add([
                categoryid,
                category,
                limit
            ]).draw( false );
            createChart();
        }


		function createModal(data, row){
			modalCategory = data[1];
			// Get the modal
			var modal = document.getElementById("myModal");

			modal.style.display = "block";

            $('#itemModal').val(data[1]);
			$('#limitModal').val(data[2]);

			$('#submitModal').off('click').on('click', function(){
				if(buttonModal){
				var category = $('#itemModal').val();
				$.ajax({
					url: "/api/category/editCategory/" + data[0],
					method: "PUT",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify(category)
				}).done(function(category){
					categories.splice(categories.indexOf(data[1]), 1);
					categories.push(category.category);
					categoryTable.row( row ).remove().draw();
                    if(data[2] == '' && $('#limitModal').val() == ''){
                        addToTable(category.categoryid, category.category, '');
                    }
                    else{
						var limit = createLimit($('#limitModal').val() ? $('#limitModal').val() : -1.0, category);
						$.ajax({
							url: "/api/limit/editLimit/" + data[0],
							method: "PUT",
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(limit)
						}).always(function(limit){
                    		addToTable(category.categoryid, category.category, limit.limit ? limit.limit : '');
						});
                    }
					createChart();
					modal.style.display = "none";
				});
			}
			});

			$('#removeLimitModal').off('click').on('click', function(){
				$.ajax({
					url: "/api/limit/deleteLimitByCategory/" + data[0],
					method: "DELETE",
					contentType: "application/json",
					dataType: "json"
				})
					categoryTable.row( row ).remove().draw();
					categoryTable.row.add([
                        data[0],
                        data[1],
                        ''
                    ]).draw( false );
					createChart();
					modal.style.display = "none";
			});
			
			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
				modal.style.display = "none";
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
			}
		}
		
	function getUser() {
		$.ajax({
				url: "/api/User/getUsername"
			}).done(function(data){			
				var x = document.getElementById("username");
				x.innerHTML = data; 
			});
	}

	function isBlank(str) {
		return (!str || /^\s*$/.test(str)); //check for empty and all blank
	}
		