class Order < ActiveRecord::Base
	belongs_to :consumer
	belongs_to :user
	has_many :ordered_items
end
