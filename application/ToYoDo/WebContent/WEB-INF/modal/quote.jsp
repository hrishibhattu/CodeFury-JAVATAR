
<div class="modal fade" id="viewQuoteModal" tabindex="-1" role="dialog"
	aria-labelledby="quoteModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="quoteModalLongTitle">Quote</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
					<table class="table table-striped table-responsive">
						<thead>
							<tr>
								<th scope="col">Order Date</th>
								<th scope="col">Customer GST No.</th>
								<th scope="col">Shipping Address</th>
								<th scope="col">Phone Number</th>
								<th scope="col">Total Order Value</th>
								<th scope="col">Status</th>
							</tr>
						</thead>
						<tbody>


							<%-- <%
							List<Quote> viewQuote = employeeService.viewQuote();
							for (Quote quote : viewQuote) {
							%>

							<tr>
								<td><%=quote.getOrder_date()%></td>
								<td><%=quote.getCustomer_gst_no()%></td>
								<td><%=quote.getCustomer_shipping_address()%></td>
								<td><%=quote.getCustomer_phone_number()%></td>
								<td>&#8377; <%=quote.getTotal_order_value()%></td>
								<td><%=quote.getStatus()%></td>
							</tr>


							<%
							}
							%> --%>

						</tbody>
					</table>

				</div>
			</div>
			<div class="modal-footer">
				<hr />
			</div>
		</div>
	</div>
</div>