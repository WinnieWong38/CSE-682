var categoryTable;
var chart;

$(document).ready(function() {
	init();
});

function init(){
	//createChart();
	createTable();
	enableListeners();
	getTotalLimit();
}


function createChart(){

	var categoryArr = [];
	$.ajax({
			url: "/api/category/getCategories"
	}).done(function(data){
		for(let item in data){
			$.ajax({
				url: "/api/expense/getTotalCostByCategory/" + data[item].categoryid,
				async: false
			}).done(function(total){
				categoryArr.push([ data[item].category, total]);
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
            if ( table.column(i).search() !== this.value ) {
                table
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
				var category = $('#category').val();
				$.ajax({
					url: "/api/category/addCategory",
					method: "POST",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify(category)
				}).done(function(category){
                    if($('#limit').val()){
                    var limit = createLimit($('#limit').val(), category, false);
                    $.ajax({
                        url: "/api/limit/addLimit",
					    method: "POST",
					    contentType: "application/json",
					    dataType: "json",
					    data: JSON.stringify(limit)
                    }).done(function(limit){
                        addToTable(category.categoryid, category.category, limit.limit)
                    });
                }
                else{
                    addToTable(category.categoryid, category.category, '')
                }
					
				});
			
			});
			$('#submitTotal').off('click').on('click', function(){
				var limit = createLimit($('#totalLimit').val(), null, true);
				$.ajax({
					url: "/api/limit/setTotalLimit",
					method: "POST",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify(limit)
				}).done(function(limit){
					$('#totalLimitText').text(limit.limit);
				})
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
			// Get the modal
			var modal = document.getElementById("myModal");

			modal.style.display = "block";

            $('#itemModal').val(data[1]);
			$('#limitModal').val(data[2]);

			$('#submitModal').off('click').on('click', function(){
				var category = $('#itemModal').val();
				$.ajax({
					url: "/api/category/editCategory/" + data[0],
					method: "PUT",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify(category)
				}).done(function(category){
					categoryTable.row( row ).remove().draw();
                    if(data[2] == '' && $('#limitModal').val() == ''){
                        addToTable(category.categoryid, category.category, '');
                    }
                    else{
						var limit = createLimit();
						$.ajax({
							url: "/api/limit/editLimit/" + data[0],
							method: "PUT",
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(limit)
						}).done(function(limit){
							console.error('limit', limit)
						});
                    }
                    addToTable(category.categoryid, category.category);
					createChart();
					modal.style.display = "none";
				});
			});

			$('#deleteModal').off('click').on('click', function(){
				$.ajax({
					url: "/api/category/deleteCategory/" + data[0],
					method: "DELETE",
					contentType: "application/json",
					dataType: "json"
				})
                    categoryTable.row( row ).remove().draw();
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
		