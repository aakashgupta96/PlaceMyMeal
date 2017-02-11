class MenusController < ApplicationController 
before_action :set_menu_items
before_action :authenticate_user!
	def show
		
	end

	def addmenuitems
		@menu_item = MenuItem.new()
	end

	def add
		mi = MenuItem.create(menu_item_params)
		mi.user = current_user
		mi.save!
		return redirect_to '/menus/show'
	end

	private

	def menu_item_params
		params.require(:menu_item).permit(:name, :price, :image)
	end
	def set_menu_items
		@menu_items = current_user.menu_items
	end
end
