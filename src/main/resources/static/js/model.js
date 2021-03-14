function createExpense (expense, category, cost) {
	var obj = {};
	obj.expense = expense;
	obj.category = category;
	obj.cost = parseInt(cost);
	return obj;
}

function createCategory (categoryId, category) {
	var obj = {};
	obj.categoryid = parseInt(categoryId);
	obj.category = category;
	return obj;
}