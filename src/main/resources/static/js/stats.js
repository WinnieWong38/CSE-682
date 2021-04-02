var summaryChart;

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

//Creates the limit/category bar chart
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

//creates the monthly trend chart
function createMonthlyTrend(){
    $.ajax({
        url: "/api/expense/getTimeseriesChart",
    }).done(function(data){
        var monthlyTrendChart = c3.generate({
            bindto: "#monthlyTrend",
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

//creates the monthly summary chart
function createBar(){
    $.ajax({
        url: "/api/expense/getMonthlySummaryBar",
    }).done(function(data){
        summaryChart = c3.generate({
            bindto: "#monthlySummaryBar",
            data: {
                columns: [
                    ['Total Expenses This Month', data[0]],
                    ['Total Monthly Limit', data[1]],
                    ['Total Monthly Income', data[2]]
                ],
                type: 'bar'
            },
            bar: {
                width: {
                    ratio: 1.0 
                }
            },
            axis: {
                rotated: true
            }        
        });
        
    });
}


//creates the total by category table
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