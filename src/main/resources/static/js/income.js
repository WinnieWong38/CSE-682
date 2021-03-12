

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
			url: "/api/income/getIncomes"
		}).done(function(data){
		console.error(data);
			for(let item in data){
			console.error(data[item]);
			categoryArr.push([ data[item].description, data[item].income]);											
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
}
		});
    
    });

	
	$(document).ready(function() {
		
	/*	$.ajax({
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
		*/ 
		
		
		
		
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
			 "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
        ]
		});
		
		$.ajax({
			url: "/api/income/getIncomes"
		}).done(function(data){
			console.error('data', data);
			for(let item in data){
				table.row.add([
					data[item].incomeid,
					data[item].description,					
					data[item].income,					
					"<input id='btnDelete' class='btn btn-success' width='15px' value='Delete' />"
				]).draw( false );
			}
		});
		
		$('#table').on('click', 'tbody tr', function () {
            table.row( this ).edit();			
        });
		
		
		$('#example tbody').on('click', '[id*=btnDelete]', function () {
            var data = table.row($(this).parents('tr')).data();
            var id = data[0];
			var description = data[1];
            var income = data[2];            
            alert("Id: " + id + "Desc : " + description + "\n" + "Income : " + income );
         
			$.ajax({
				type: "DELETE",
				url: "/api/income/deleteVal/"+id
			}).done(function()
			{
				//table redraw
				table.redraw();
			});
		});
				
		
		
		$('#submit').off('click').on('click', function(){
		var saveObj = {};
		saveObj.description = $('#description').val();		
		saveObj.income = parseInt($('#income').val());
		console.log(saveObj);
		$.ajax({
			url: "/api/income/addIncome",
			method: "POST",
			contentType: "application/json",
    		dataType: "json",
			data: JSON.stringify(saveObj)
		});
		//table redraw
				table.redraw();
	/*	table.row.add([
					saveObj.description,					
					saveObj.income
				]).draw( false );
				$('#desctiption').val('');
				$('#income').val('');
		*/		
	});
	
	} );
	
	
	