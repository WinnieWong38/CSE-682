function createExpense (expense, category, cost, date, isPaid) {
	var obj = {};
	obj.expense = expense;
	obj.category = category;
	obj.cost = parseInt(cost);
	obj.date = date;
	obj.isPaid = isPaid;
	return obj;
}

function createCategory (categoryId, category) {
	var obj = {};
	obj.categoryid = parseInt(categoryId);
	obj.category = category;
	return obj;
}

function createLimit(limit, category, isTotal){
	var obj = {};
	obj.limit = parseInt(limit);
	obj.category = category;
	obj.isTotal = isTotal;
	return obj;
}