var gaugeChart;

$(document).ready(function() {
	init();
});

function init(){
	createBarLimitCat();
    createMonthlyTrend();
    createBar();
    createTable();
    getUser();
}

function createBarLimitCat(){
    $.ajax({
        url: "/api/limit/getLimitAndCategoriesAndExpenses"
    }).done(function(data){
        
        var categories = data[2];
        categoriesArray = [];
        for(let category in categories){
            categoriesArray.push(categories[category]);
        }

        var trace1 = {
            x: categoriesArray,
            y: data[0],
            name: 'Limit',
            type: 'bar'
          };

          var trace2 = {
            x: categoriesArray,
            y: data[1],
            name: 'Total Expenses',
            type: 'bar'
          };

          var data = [trace1, trace2];

          var layout = {barmode: 'group'};

          Plotly.newPlot('barChart', data, layout);

    });
}

function createMonthlyTrend(){
    $.ajax({
        url: "/api/expense/getTimeseriesChart",
    }).done(function(data){
        console.error('data', data)
        var monthlyTrendChart = c3.generate({
            bindto: "#montlyTrend",
            data: {
                x: 'Dates',
                columns: data
            },
            axis: {  
                x: {
                    type: 'timeseries',
                    tick: {
                        format: '%Y-%m-%d'
                    }
                }
            }
        });
    });
    
}

function createBar(){
    $.ajax({
        url: "/api/expense/getTotalExpenseToLimitRatio",
    }).done(function(data){
        console.error('data2', data)
        gaugeChart = c3.generate({
            bindto: "#expensetoLimitRatio",
            data: {
                columns: [
                    ['Ratio', data]
                ],
                type: 'bar'
            },
            bar: {
                width: {
                    ratio: 0.5 // this makes bar width 50% of length between ticks
                }
                // or
                //width: 100 // this makes bar width 100px
            },
            axis: {
                rotated: true
            }        
        });
        
    });
}

function createTable(){	
    $('#expensesByCategory thead tr').clone(true).appendTo( '#expensesByCategory thead' );
    $('#expensesByCategory thead tr:eq(1) th').each( function (i) {
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
    
    var table = $('#expensesByCategory').DataTable({});
    
    $.ajax({
        url: "/api/expense/getTotalCostCurrentMonthByCategory"
    }).done(function(data){
        for(let item in data[0]){
            table.row.add([
                data[0][item],
                data[1][item]
            ]).draw( false );
        }
    });
}

	function getUser() {
        $.ajax({
                url: "/api/User/getUsername"
            }).done(function(data){			
                var x = document.getElementById("username");
                x.innerHTML = data; 
            });
	}