class RenameMenuIdToUserId < ActiveRecord::Migration
  def change
  	rename_column :menu_items, :menu_id, :user_id
  end
end
