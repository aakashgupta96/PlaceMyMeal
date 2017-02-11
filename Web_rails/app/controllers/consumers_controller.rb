class ConsumersController < ApplicationController
	def outlets
		@messages = Message.where(consumer_id: current_consumer.id).where(seen:false)
		@outlets = User.all
		@messages.each do |message|
			message.seen = true;
		end
	end

	def menu
		@company = User.find(params[:user_id]);
		@items = @company.menu_items
	end

	def add_items
		o = Order.new()
		u = User.find(params[:company_id]);
		o.user = u
		o.consumer = current_consumer
		o.save!
		items = params["ordered_items"]
		items.each do |key,value|
			oi = OrderedItem.new()
			oi.menu_item_id = key
			oi.quantity = value
			oi.order_id = o.id
			oi.save!
		end
		return redirect_to "/consumers/order"
	end

	def order
		
	end
end
