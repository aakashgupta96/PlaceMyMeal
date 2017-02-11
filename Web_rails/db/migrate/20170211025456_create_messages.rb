class CreateMessages < ActiveRecord::Migration
  def change
    create_table :messages do |t|
      t.string :notification
      t.boolean :seen
      t.integer :consumer_id
      
      t.timestamps null: false
    end
  end
end
