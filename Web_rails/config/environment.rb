# Load the Rails application.
require File.expand_path('../application', __FILE__)
require 'rest-firebase'

require 'carrierwave/orm/activerecord'
# Initialize the Rails application.
Rails.application.initialize!
Resque.enqueue(DbToFirebase)
Resque.enqueue(FirebaseToDb)