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