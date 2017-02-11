class CreateOrderedItems < ActiveRecord::Migration
  def change
    create_table :ordered_items do |t|
      t.integer :menu_item_id
      t.integer :quantity

      t.timestamps null: false
    end
  end
end
