class CreateMenuItems < ActiveRecord::Migration
  def change
    create_table :menu_items do |t|
      t.string :name
      t.integer :price
      t.integer :menu_id

      t.timestamps null: false
    end
  end
end
