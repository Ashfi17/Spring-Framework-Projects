Feature: Blogs data services test

	Scenario: create new Blog
		When the client makes a call to post /blogs/createBlog
		Then the client receives status of code 200 
		And and the client receives confirmation message