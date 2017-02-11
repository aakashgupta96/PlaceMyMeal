class CompanyController < ApplicationController
	before_action :pre 
	def home
		
	end
	
	def orders
		@orders = @db.get("company")
		#byebug
	end


	private

	def pre
		@db = RestFirebase.new({ site: "https://placemymeal.firebaseio.com", secret: "AIzaSyA26PW4S51uQRZ0mPKCrGJUTduyUMjDU3w", :d => {:auth_data => 'something'},:log_method => method(:puts),:timeout => 10,:max_retries => 3,:retry_exceptions =>[IOError, SystemCallError, Timeout::Error],:error_callback => method(:p),:auth_ttl => 82800,:auth => false})
	end
end
