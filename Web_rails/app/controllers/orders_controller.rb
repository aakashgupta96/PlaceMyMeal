class OrdersController < ApplicationController
	before_action :authenticate_user!, except: [:index]
	before_action :set_order, except: [:index, :orders]
	def index
		if current_user
			return redirect_to '/orders'
		elsif current_consumer
			return redirect_to '/consumers/outlets'
		else
			return redirect_to new_user_session_path
		end
	end

	def orders
		@orders = current_user.orders.where(done: false)
	end

	def mark_done
		@order.done = true
		@order.save
		redirect_to '/orders'
		m = Message.new()
		m.notification = "Your order is ready"
		m.consumer = @order.consumer
		m.seen = false
		m.save!
	end


	private

	def set_order
		@order = Order.find(params[:order_id])
	end

end
