
### XUIFramework

A framework for making android boilerplate easy, our goal is to write less common codes in every project. 

### Getting started
#### project grade:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
#### app module grade:
	def latestVersion = '{put_latest_version_here}'
	
    //image picker and cropper util
    implementation "com.github.ehsanhvd.XFramework2:cropper:$latestVersion" 
    
    //runtime form generator and validator
    implementation "com.github.ehsanhvd.XFramework2:xforms:$latestVersion"
    
    //persian date picker and utils
    implementation "com.github.ehsanhvd.XFramework2:xpersiandatepicker:$latestVersion"
    
    //common utils
    implementation "com.github.ehsanhvd.XFramework2:xutils:$latestVersion"
    
    //custom views
    implementation "com.github.ehsanhvd.XFramework2:xview:$latestVersion"

### Contribution
Any pull requests, code improvement recommendations and bug reports are welcome.
