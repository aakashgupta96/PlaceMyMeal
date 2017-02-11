class DbToFirebase
	@queue = :db_to_firebase

	def self.perform()
	
		loop do
			# Update company data on firebase
			@db = RestFirebase.new({ site: "https://placemymeal.firebaseio.com", secret: "AIzaSyA26PW4S51uQRZ0mPKCrGJUTduyUMjDU3w", :d => {:auth_data => 'something'},:log_method => method(:puts),:timeout => 10,:max_retries => 3,:retry_exceptions =>[IOError, SystemCallError, Timeout::Error],:error_callback => method(:p),:auth_ttl => 82800,:auth => false})
			companies = User.all
			companies.each do |company|
				@db.put("company/#{company.id}", {id: company.id.to_s, name: company.name})
			end

			#updating menu_items
			menu_items = MenuItem.all
			menu_items.each do |item|
				@db.put("menu_items/#{item.id}",{id: item.id.to_s, name: item.name, price: item.price.to_s, company_id: item.user_id.to_s})
			end

			#updating order with their status
			orders = Order.all
			orders.each do |order|
				@db.put("orders/#{order.id}",{id: order.id.to_s, company_id: order.user_id.to_s, consumer_id: order.consumer_id.to_s, done: order.done})
			end
			sleep(5);
		end
	end
end