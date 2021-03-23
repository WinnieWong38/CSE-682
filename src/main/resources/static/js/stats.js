$(document).ready(function() {
	init();
});

function init(){
	createBarLimitCat();
    createMonthlyTrend();
    createGauge();
    createTable();
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

function createGauge(){
    $.ajax({
        url: "/api/expense/getTotalExpenseToIncomeRatio",
    }).done(function(data){
        console.error('data2', data)
        var gaugeChart = c3.generate({
            bindto: "#incomeGuage",
            data: {
                columns: [
                    ['data', data]
                ],
                type: 'gauge'
            },
            color: {
                pattern: ['#60B044'], 
            },
            size: {
                height: 180
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
    
    var table = $('#expensesByCategory').DataTable({
    });

    $('#expensesByCategory tbody').on('click', 'tr', function () {
        createModal(table.row( this ).data(), $(this));
    } );
    
    // $.ajax({
    //     url: "/api/expense/getExpenses"
    // }).done(function(data){
    //     for(let item in data){
    //         table.row.add([
    //             data[item].category,
    //             data[item].total
    //         ]).draw( false );
    //     }
    // });
}