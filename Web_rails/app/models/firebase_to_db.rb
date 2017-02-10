class FirebaseToDb
	@queue = :firebase_to_db

	def self.perform()
		
		loop do
			@db = RestFirebase.new({ site: "https://placemymeal.firebaseio.com", secret: "AIzaSyA26PW4S51uQRZ0mPKCrGJUTduyUMjDU3w", :d => {:auth_data => 'something'},:log_method => method(:puts),:timeout => 10,:max_retries => 3,:retry_exceptions =>[IOError, SystemCallError, Timeout::Error],:error_callback => method(:p),:auth_ttl => 82800,:auth => false})
			
			# Update consumer data from firebase
			consumers = db.get("consumers")
			companies.each do |company|
				db.put("testing_company/#{company.id}", {id: company.id, name: company.name})
			end

			#updating menu
			menus = Menu.all
			menus.each do |menu|
				db.put("testing_menu/#{menu.id}",{id: menu.id, company_id: menu.user_id})
			end

			#updating menu_items
			menu_items = MenuItem.all
			menu_items.each do |item|
				db.put("testing_menu_items/#{item.id}",{id: item.id, name: item.name, price: item.price, menu_id: item.menu_id})
			end

			#updating order with their status
			orders = Order.all
			orders.each do |order|
				db.put("testing_orders/#{order.id}",{id: order.id, company_id: order.user_id, consumer_id: order.consumer_id, done: order.done})
			end
		end
	end
end