
	new FSM("01", "eo")
		.add(new State("Even", true)
			.add(new Transition('0', 'o', "Odd"))
			.add(new Transition('1', 'e', "Even")))
		.add(new State("Odd", false)
			.add(new Transition('0', 'e', "Even"))
			.add(new Transition('1', 'o', "Odd")));
	
	