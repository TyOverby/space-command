package shared.main.entity.components;

import org.newdawn.fizzy.Body;
import org.newdawn.fizzy.DynamicBody;
import org.newdawn.fizzy.Shape;
import org.newdawn.fizzy.StaticBody;
import org.newdawn.fizzy.World;

import shared.main.entity.Component;
import shared.math.Vector2f;

public class PhysicsComponent extends Component{
	private static final long serialVersionUID = -8935260732944808861L;
	
	private final float LINEAR_DAMPENING = 0.5f;
	private final float ANGULAR_DAMPENING = 0.5f;
	
	private final float density;
	private final float restitution;
	private final float friction;

	private final Body<PhysicsComponent> body;

	public PhysicsComponent(Shape shape, Vector2f position, Vector2f velocity, float rotation, float density, float restitution, float friction,  World world, boolean dynamic) {
		super("PhysicsComponent");

		shape.setDensity(density);
		shape.setRestitution(restitution);
		shape.setFriction(friction);


		if(dynamic){
			body = new DynamicBody<PhysicsComponent>(shape,position.getX(),position.getY());
		}
		else{
			body = new StaticBody<PhysicsComponent>(shape,position.getX(),position.getY());
		}
		body.setUserData(this);
		
		body.setLinearDamping(LINEAR_DAMPENING);
		body.setAngularDamping(ANGULAR_DAMPENING);
		
		world.add(body);

		body.setVelocity(velocity.getX(), velocity.getY());
		body.setRotation((float) Math.toRadians(rotation));
		
		


		this.density = density;
		this.restitution = restitution;
		this.friction = friction;
	}

	@Override
	public void update(long delta) {
		this.setPosition(new Vector2f(body.getX(),body.getY()));
		this.parent.setRotation((float) Math.toDegrees(body.getRotation()));
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
		return density;
	}
	public float getResitution(){
		return restitution;
	}
	public float getFriction(){
		return friction;
	}

	public Body<PhysicsComponent> getBody(){
		return this.body;
	}
}
