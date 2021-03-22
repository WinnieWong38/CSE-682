$(document).ready(function() {
	init();
});

function init(){
	createBarLimitCat();
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

