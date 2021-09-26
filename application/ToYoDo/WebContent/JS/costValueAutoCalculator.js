	var v = document.getElementsByClassName("addProducts");

	for (var i = 0; i < v.length; i++) {
		var btn = v[i];
		btn.addEventListener('click', UpdateCartTotal);
	}

	
	var quantity = document.getElementsByClassName('product-quantity')

	for (var i = 0; i < quantity.length; i++) {

		var input = quantity[i];
		// console.log("Input Value QTY: " + input.value);
		input.addEventListener('change', quantitychanged);

	}

	function quantitychanged(event) {

		var input = event.target

		if (isNaN(input.value) || input.value < 0) {
			input.value = 0
		}
		UpdateCartTotal()
	}

	function UpdateCartTotal() {

		var item = document.getElementsByClassName("products-rows")[0]
		var cartrows = item.getElementsByClassName("product-row")
		
		var shipping_cost = 0;
		var total = 0;
		var total_cost = 0;
		const product_map = new Map();
		var json = "";
		for (var i = 0; i < cartrows.length; i++) {
			var cr = cartrows[i]

			var priceelement = cr.getElementsByClassName("product-price")[0]
			var qu = cr.getElementsByClassName("product-quantity")[0]
			
			console.log("CART Value QTY: " + qu.value);
			
			var category = cr.getElementsByClassName("product-category")[0]

			var p = priceelement.innerText;
			var id = cr.getElementsByClassName('product-id')[0]
			var q = qu.value;
			
			
			var cat = category.innerText;
			if (q) {
				product_map.set(id.innerText, q);
				console.log(product_map)
				console.log(product_map.size)
				
			}
			
			function mapToObj(product_map){
				  const obj = {}
				  for (let [k,v] of product_map)
				    obj[k] = v
				  return obj
			}
			const myJson = {};
			myJson.product_map = mapToObj(product_map);
			json = JSON.stringify(myJson);

			console.log(json)
			
			
			if (cat === 'Level 1') {
				shipping_cost = shipping_cost + (p * 0.05) * q
				//console.log("level 1 shipping cos")
			}

			else if (cat === 'Level 2') {
				shipping_cost = shipping_cost + (p * 0.03) * q
				//console.log("level 2 shipping cos")
			}

			else {
				shipping_cost = shipping_cost + (p * 0.02) * q
				//console.log("level 3 shipping cos")
			}

			total = total + (p * q)

		}
		
		if (total > 100000)
			shipping_cost = 0;
		total_cost = total + shipping_cost

		document.getElementById("shippingCost").value = shipping_cost
		document.getElementById("totalOrderValue").value = total_cost;
		document.getElementById("product-quantity-map").value = json;
		
	}