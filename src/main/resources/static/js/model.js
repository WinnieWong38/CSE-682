function createExpense (expense, category, cost, date, isPaid) {
	var obj = {};
	obj.expense = expense;
	obj.category = category;
	obj.cost = parseFloat(cost);
	obj.date = date;
	obj.isPaid = isPaid;
	return obj;
}

function createCategory (categoryId, category) {
	var obj = {};
	obj.categoryid = parseFloat(categoryId);
	obj.category = category;
	return obj;
}

function createLimit(limit, category, isTotal){
	var obj = {};
	obj.limit = parseFloat(limit);
	obj.category = category;
	obj.isTotal = isTotal;
	return obj;
}

function createIncome(income, cost){
	var obj = {};
	obj.income = income;
	obj.cost = parseFloat(cost);
	return obj;
}