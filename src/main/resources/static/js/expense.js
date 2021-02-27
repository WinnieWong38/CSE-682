

/*
new Vue({
	el: '#app',
	data(){
	return {
		items: []
	}
	},
	created(){
		fetch(url)
			.then(response => {
				return response.json();
			})
			.then(items => {
				this.items = items;
			})
	}
});
*/

$(document).ready(function() {
	var categoryArr = [];
	$.ajax({
			url: "/api/category/getCategories"
		}).done(function(data){
		console.error(data);
			for(let item in data){
			console.error(data[item]);
				$.ajax({
					url: "/api/expense/getTotalCostByCategory/" + data[item].categoryid,
					async: false
				}).done(function(total){
				console.error(total);
					categoryArr.push([ data[item].category, total]);
					if(data.length === categoryArr.length){
					console.error(data, categoryArr);
				var chart = c3.generate({
        bindto: "#c3chart_category",
        data: {
            columns: categoryArr,
            type: 'donut',

            onclick: function(d, i) { console.log("onclick", d, i); },
            onmouseover: function(d, i) { console.log("onmouseover", d, i); },
            onmouseout: function(d, i) { console.log("onmouseout", d, i); },

            colors: {
                Men: '#5969ff',
                Women: '#ff407b',
                Accessories: '#25d5f2',
                Children: '#ffc750',
                Apperal: '#2ec551',



            }
        },
        donut: {
            label: {
                show: false
            }
        },



    
    });
				};
			});
			}
		});
    
    });
	
	$(document).ready(function() {
		
		$.ajax({
			url: "/api/category/getCategories"
		}).done(function(data){
			for(let item in data){
				var x = document.getElementById("category");
				var option = document.createElement("option");
				option.value = data[item].categoryid;
				option.text = data[item].category;
				x.add(option);
			}
		});
		 
		
		
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
		
		var table = $('#example').DataTable({
			
		});
		
		$.ajax({
			url: "/api/expense/getExpenses"
		}).done(function(data){
			console.error('data', data);
			for(let item in data){
				table.row.add([
					data[item].expense,
					data[item].category.category,
					data[item].cost
				]).draw( false );
			}
		});
		
		$('#submit').off('click').on('click', function(){
		var saveObj = {};
		saveObj.expense = $('#item').val();
		saveObj.category = {'categoryid' : parseInt($('#category option:selected').val()), 'category': $('#category option:selected').text()};
		saveObj.cost = parseInt($('#price').val());
		console.log(saveObj);
		$.ajax({
			url: "/api/expense/addExpense",
			method: "POST",
			contentType: "application/json",
    		dataType: "json",
			data: JSON.stringify(saveObj)
		});
		table.row.add([
					saveObj.expense,
					saveObj.category.category,
					saveObj.cost
				]).draw( false );
				$('#item').val('');
				$('#price').val('');
	});
	} );
	
	
	