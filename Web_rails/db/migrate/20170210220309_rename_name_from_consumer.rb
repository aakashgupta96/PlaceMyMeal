class RenameNameFromConsumer < ActiveRecord::Migration
  def change
  	rename_column :consumers, :name, :email
  end
end
