class AddImageToMenuItems < ActiveRecord::Migration
  def change
    add_column :menu_items, :image, :string
  end
end
