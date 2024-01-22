## Migrant-Matcher

# PHASE 1

The MigrantMatcher app is being developed with the aim of facilitating mass migrant aid. In situations of war, volcanoes or other calamities, this application will help migrants to receive help (whether in items or accommodation) more effectively.

- Use Case 1: Register help
  
This use case allows volunteers to provide a type of help on the platform.

Use Case Description:

The volunteer identifies himself with his telephone number.
The system asks the volunteer to indicate the type of help they intend to offer.
If the volunteer intends to offer accommodation,
The volunteer indicating the number of people that this accommodation houses.
The system returns the list of regions in the country.
The volunteer indicates the region where the accommodation is located.
If the volunteer intends to offer an item (e.g. mattresses, clothes, toys),
The volunteer indicates the description of the item.
In either case, the system sends an SMS notification to the volunteer with a unique code.
The user tells the system the unique code to confirm the offer of help.

- Use Case 2: Seek Help
  
This use case allows migrants to search for and accept offered help.

Use Case Description:

Alternatively,
The migrant indicates that he wishes to register individually, indicating his name and telephone number.
Or indicate that you want to register your family, indicating the number of people.
In this case, the system asks for the name and contact details of the head of the couple.
The migrant indicates the name and corresponding contact.
The system asks for data from another family member.
The migrant indicates the name of the other member.
The last two steps can be repeated as long as the migrant understands.
The migrant then asks for a list of regions to which they can move.
The system returns the list of regions in the country.
The migrant indicates the region to which he will move.
The system returns a list of possible help (both accommodation in that region and items).

- Extension

5a:
5. The System indicates that there is no help in this region.
6. The migrant indicates that he wants to be notified when he exists in that region.

# PHASE 2

In this phase 2 of the project, we intend to implement the project from an object-oriented perspective. At the moment, we just want to have objects in memory and there is no need for a database or persistence.

- Objectives:
The project will be evaluated on two criteria:

- Functional Assessment: The project will be evaluated according to the richness of the JUnit tests designed to test the two use cases (identified in goal 1)

- Non-Functional Assessment: The quality of the design and organization of the code will be assessed, with particular emphasis on the applied Usage Standards. In addition to the GRASP standards, we will take into account the Façade, DTO, Strategy, Adapter, Pure Fabrication, Factory, Singleton, Composite, Builder, Chain of Command and Observer standards. Of these patterns, you should only use those that make sense. The granularity of git commits will also be evaluated.

- Changes in use cases:
You must take into account the following additional requirements:

The system must be configured with ways to order available aid. One of the possible ways is by increasing availability date. Another way will be first by accommodation, then by other items, ordered within each category randomly.
The SMS gateway providers provided in the attached file must be incorporated. You can create others if they prove necessary for the tests.
The migrant chooses the help he wants to receive from those listed.
The system records this information.
The two previous steps can be repeated as many times as the migrant needs.
Finally, the migrant indicates that he wants to confirm.
The system records the granting of aid to that migrant, and sends an SMS to the volunteers who offered their respective aid.
