class AddOrderIdToOrderedItems < ActiveRecord::Migration
  def change
  	add_column :ordered_items, :order_id, :integer
  end
end
