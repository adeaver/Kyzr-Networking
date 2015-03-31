from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def index():
	return render_template('index.html')

# BELOW IS THE ONLY IMPORTANT PART

@app.route('/dbadd', methods=['GET', 'POST'])
def dbadd():
	lat = -9999
	lng = -9999
	id1 = ""
	id2 = ""
	if request.method=="POST":
		for key in request.form.keys():
			if key == "lat":
				try:
					lat = float(request.form[key])
				except:
					lat = -9999
			elif key == "lng":
				try:
					lng = float(request.form[key])
				except:
					lng = -9999
			elif key == "id1":
				id1 = request.form[key]
			elif key == "id2":
				id2 = request.form[key]

		if(lat != -9999 and lng != -9999 and id1 != "" and id2 != ""):
			# add data to database
			return "Success"
	
	return "Request Failed"

@app.route('/dbreturn', methods=['GET', 'POST'])
def dbreturn():
	oldid = ""
	newid = ""
	if request.method=="POST":
		if "old_id" in request.form.keys():
			oldid = request.form["old_id"]
			# get data from database
			# newid = something
			return newid

	return "Request Failed"


if __name__ == '__main__':
    app.run(debug=True)