var table;
var chart;

$(document).ready(function() {
	init();
	
	// ============================================================== 
    // Notification list
    // ============================================================== 
    if ($(".notification-list").length) {

        $('.notification-list').slimScroll({
            height: '250px'
        });

    }

       
     // ============================================================== 
    // tooltip
    // ============================================================== 
    if ($('[data-toggle="tooltip"]').length) {
            
            $('[data-toggle="tooltip"]').tooltip()

        }

     // ============================================================== 
    // popover
    // ============================================================== 
       if ($('[data-toggle="popover"]').length) {
            $('[data-toggle="popover"]').popover()

    }
 
	
});

function init(){
	createForm("category");
	createChart();
	createTable();
	enableListeners();
	getUser();
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
	
	function createForm(element){
		$('#' + element).empty();
		return $.ajax({
			url: "/api/category/getCategories"
		}).done(function(data){
			for(let item in data){
				var x = document.getElementById(element);
				var option = document.createElement("option");
				option.value = data[item].categoryid;
				option.text = data[item].category;
				x.add(option);
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
		
		table = $('#example').DataTable({
			"columnDefs": [
				{
					"targets": [ 0 ],
					"visible": false,
					"searchable": false
				}
			]
		});

		$('#example tbody').on('click', 'tr', function () {
			createModal(table.row( this ).data(), $(this));
		} );
		
		$.ajax({
			url: "/api/expense/getExpenses"
		}).done(function(data){
			for(let item in data){
				table.row.add([
					data[item].expenseid,
					data[item].expense,
					data[item].category.category,
					data[item].cost,
					data[item].date,
					data[item].isPaid ? "<i class='fas fa-check'></i>" : "",
				]).draw( false );
			}
		});
	}
		
		function enableListeners(){
			//Add new expense
			$('#submit').off('click').on('click', function(){
				var expense = createExpense($('#item').val(), createCategory($('#category option:selected').val(), $('#category option:selected').text()), $('#price').val(), $('#date').val(), $('#isPaid').prop('checked'));
				$.ajax({
					url: "/api/expense/addExpense",
					method: "POST",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify(expense)
				}).done(function(expense){
					table.row.add([
						expense.expenseid,
						expense.expense,
						expense.category.category,
						expense.cost,
						expense.date,
						expense.isPaid ? "<i class='fas fa-check'></i>" : ""
					]).draw( false );
					createChart();
					$('#item').val('');
					$('#price').val('');
					$('#submit').addClass('disabled');
				}).fail(function(){
					alert('Invalid entry');
				});
			
			});

			$('#submit').addClass('disabled');
			$('.card-body').off('change input').on('change input', function(){
				if(!isBlank($('#item').val()) && !isBlank($('#price').val()) && !isBlank($('#date').val())){
					$('#submit').removeClass('disabled');
				}
				else{
					$('#submit').addClass('disabled');
				}
			});
		}

		function createModal(data, row){
			// Get the modal
			var modal = document.getElementById("myModal");

			modal.style.display = "block";
			$.when(createForm("categoryModal")).done(function(){
				$('#itemModal').val(data[1]);
				$('#categoryModal option:contains('+data[2]+')').attr('selected', 'selected')
				$('#priceModal').val(data[3]);
				$('#dateModal').val(data[4]);
				$('#isPaidModal').prop('checked', data[5] != "");
			});

			$('#submitModal').off('click').on('click', function(){
				var expense = createExpense($('#itemModal').val(), createCategory($('#categoryModal option:selected').val(), $('#categoryModal option:selected').text()), $('#priceModal').val(), $('#dateModal').val(), $('#isPaidModal').prop('checked'));
				$.ajax({
					url: "/api/expense/editExpense/" + data[0],
					method: "PUT",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify(expense)
				}).done(function(expense){
					table.row( row ).remove().draw();
					table.row.add([
						expense.expenseid,
						expense.expense,
						expense.category.category,
						expense.cost,
						expense.date,
						expense.isPaid ? "<i class='fas fa-check'></i>" : "",
					]).draw( false );
					createChart();
					modal.style.display = "none";
				});
			});

			$('#deleteModal').off('click').on('click', function(){
				$.ajax({
					url: "/api/expense/deleteExpense/" + data[0],
					method: "DELETE",
					contentType: "application/json",
					dataType: "json"
				})
					table.row( row ).remove().draw();
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

		function isBlank(str) {
			return (!str || /^\s*$/.test(str)); //check for empty and all blank
		}

		function getUser() {
			$.ajax({
					url: "/api/User/getUsername"
				}).done(function(data){			
					var x = document.getElementById("username");
					x.innerHTML = data; 
				});
		}
		