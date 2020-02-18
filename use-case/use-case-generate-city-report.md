# USE CASE: 2 Generate City Report


### Goal in Context

As an *Organization that reports on population* we want *to produce a report on the cities of the world* too allow *for easy access to information regarding cities and their populous*

### Scope

Organization.

### Level

Important task.

### Preconditions

We know the role.  Database contains current world and population data.

### Success End Condition

A report is available for organization to provide for staff and public.

### Failed End Condition

No report is produced.

### Primary Actor

Population Information team.

### Trigger

A request for population information is sent to us.

## MAIN SUCCESS SCENARIO

1. User selects report to find individual report 
2. User inputs to receive specific report
3. Report is then captured and is used further for information analysis

## EXTENSIONS

1. **Report selection**:
    1. All the cities in the world organised by largest population to smallest.
    2. All the cities in a continent organised by largest population to smallest.
    2. All the cities in a region organised by largest population to smallest.
    2. All the cities in a country organised by largest population to smallest.
    2. All the cities in a district organised by largest population to smallest.
    
2. **User input report selection**
    1. The top N populated cities in the world where N is provided by the user.
    1. The top N populated cities in a continent where N is provided by the user.
    1. The top N populated cities in a region where N is provided by the user.
    1. The top N populated cities in a country where N is provided by the user.
    1. The top N populated cities in a district where N is provided by the user.
    
## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release v0.1-alpha-3
