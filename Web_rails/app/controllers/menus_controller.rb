class MenusController < ApplicationController 
before_action :set_menu
before_action :authenticate_user!
	def show
		@menu_items = @menu.menu_items
	end

	def addmenuitems
		@menu_item = MenuItem.new()
	end

	def add
		mi = MenuItem.create(menu_item_params)
		byebug
		mi.menu = @menu
		mi.save!
		return redirect_to '/menus/show'
	end

	private

	def menu_item_params
		params.require(:menu_item).permit(:name, :price, :image)
	end
	def set_menu
		if(current_user.menus.length == 0)
			@menu = Menu.new()
			@menu.user = current_user
			@menu.save
		else
			@menu = current_user.menus.first
		end
	end
end
