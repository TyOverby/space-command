package shared.main.entity.components;

import shared.main.entity.Component;
import shared.math.Vector2f;

public class PhysicsComponent extends Component{
	private static final long serialVersionUID = -8935260732944808861L;

	private final float mass;
	private final float radius;

	private boolean canCollide = true;

	public PhysicsComponent(float mass, float radius) {
		super("PhysicsComponent");

		this.mass = mass;
		this.radius = radius;
	}

	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub

	}

	public boolean isColliding(PhysicsComponent other){
		float dist = Vector2f.distance(this.getPosition(),other.getPosition());
		float combRadius = this.getRadius()+other.getRadius();

		if(dist<=combRadius){
			return true;
		}
		return false;
	}
	public boolean contains(Vector2f point){
		float dist = Vector2f.distance(this.getPosition(),point);
		float combRadius = this.getRadius();
		
		if(dist<combRadius){
			return true;
		}
		return false;
	}

	public void collide(PhysicsComponent other){
		Vector2f diff = this.getVelocity().minus(other.getVelocity());
		System.out.println("diff " + diff);
		float totalMass = this.getMass() + other.getMass();
		
		System.out.println(this.getMass()/totalMass);
		System.out.println(other.getMass()/totalMass);
		
		Vector2f v1f = this.getVelocity().minus(diff.times(other.getMass()/totalMass));
		Vector2f v2f = other.getVelocity().plus(diff.times(this.getMass()/totalMass));
		
		System.out.println("v1 " + v1f);
		System.out.println("v2 " + v2f);
		
		this.setVelocity(v1f);
		other.setVelocity(v2f);

		this.setCollidable(false);
		other.setCollidable(false);
	}

	public void collide2(PhysicsComponent ball){
		Vector2f Dn = this.getPosition().minus(ball.getPosition());

		// The distance between the balls
		float delta = Dn.getMagnitude();

		// The normal vector of the collision plane
		Dn.normalize();

		// The tangential vector of the collision plane
		Vector2f Dt = new Vector2f(Dn.getY(), -Dn.getX());

		// avoid division by zero
		if (delta == 0)
		{
			ball.getPosition().plusEquals(new Vector2f(0.01f,0));
			return;
		}

		//the masses of the two balls
		float m1 = this.getMass();
		float m2 = ball.getMass();
		float M = m1 + m2;

		// minimum translation distance to push balls apart after intersecting
		Vector2f mT = Dn.times(((this.getRadius() + ball.getRadius() - delta)));

		// push the balls apart proportional to their getMass()
		this.setPosition(this.getPosition().plus((mT.times(m2/M))));
		ball.setPosition(ball.getPosition().minus((mT.times(m1/M))));

		// the velocity vectors of the balls before the collision
		Vector2f v1 = this.getVelocity();
		Vector2f v2 = ball.getVelocity();

		// split the velocity vector of the first ball into a normal and a
		// tangential component in respect of the collision plane
		Vector2f v1n = Dn.times(Vector2f.dot(v1, Dn));
		Vector2f v1t = Dt.times(Vector2f.dot(v1, Dt));

		// split the velocity vector of the second ball into a normal and a
		// tangential component in respect of the collision plane
		Vector2f v2n = Dn.times(Vector2f.dot(v2, Dn));
		Vector2f v2t = Dt.times(Vector2f.dot(v2, Dt));

		// calculate new velocity vectors of the balls, the tangential component
		// stays the same, the normal component changes analog to the 1-Dimensional case
		Vector2f v1f = v1t.plus(Dn.times(((m1 - m2) / M * v1n.getMagnitude() + 2 * m2 / M * v2n.getMagnitude())));
		Vector2f v2f = v2t.minus(Dn.times(((m2 - m1) / M * v2n.getMagnitude() + 2 * m1 / M * v1n.getMagnitude())));
		
		this.setVelocity(v1f);
		ball.setVelocity(v2f);
		
		this.setCollidable(false);
		ball.setCollidable(false);
	}


	public void setCollidable(boolean collidable){
		this.canCollide = collidable;
	}
	public boolean isCollidable(){
		return this.canCollide;
	}

	public Vector2f getPosition(){
		return this.parent.getPosition();
	}
	public void setPosition(Vector2f position){
		this.parent.setPosition(position);
	}

	public void setVelocity(Vector2f velocity){
		MovingComponent mc = (MovingComponent) parent.getComponent(MovingComponent.class);
		mc.setVelocity(velocity);
	}
	public Vector2f getVelocity(){
		MovingComponent mc = (MovingComponent) parent.getComponent(MovingComponent.class);
		return mc.getVelocity();
	}

	public float getMass(){
		return mass;
	}
	public float getRadius(){
		return radius;
	}
}
